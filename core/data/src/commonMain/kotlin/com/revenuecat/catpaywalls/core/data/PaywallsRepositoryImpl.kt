/*
 * Copyright (c) 2025 RevenueCat, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.revenuecat.catpaywalls.core.data

import com.revenuecat.purchases.kmp.Purchases
import com.revenuecat.purchases.kmp.models.CustomerInfo
import com.revenuecat.purchases.kmp.models.Offering
import com.revenuecat.purchases.kmp.models.StoreTransaction
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.suspendCancellableCoroutine
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException

internal class PaywallsRepositoryImpl : PaywallsRepository {
  override fun fetchOffering(): Flow<Result<Offering>> = flow {
    try {
      val offerings =
        suspendCancellableCoroutine { cont ->
          Purchases.sharedInstance.getOfferings(
            onError = { error -> cont.resumeWithException(Exception(error.message)) },
            onSuccess = { offerings -> cont.resume(offerings) },
          )
        }
      val currentOffering = offerings.current
      if (currentOffering != null) {
        emit(Result.success(currentOffering))
      } else {
        emit(Result.failure(IllegalStateException("No current offering available")))
      }
    } catch (e: Exception) {
      emit(Result.failure(e))
    }
  }.flowOn(Dispatchers.IO)

  override fun fetchCustomerInfo(): Flow<Result<CustomerInfo>> = flow {
    try {
      val customerInfo =
        suspendCancellableCoroutine { cont ->
          Purchases.sharedInstance.getCustomerInfo(
            onError = { error -> cont.resumeWithException(Exception(error.message)) },
            onSuccess = { info -> cont.resume(info) },
          )
        }
      emit(Result.success(customerInfo))
    } catch (e: Exception) {
      emit(Result.failure(e))
    }
  }.flowOn(Dispatchers.IO)

  override fun awaitPurchase(packageId: String): Flow<Result<StoreTransaction>> = flow {
    try {
      val offerings =
        suspendCancellableCoroutine { cont ->
          Purchases.sharedInstance.getOfferings(
            onError = { error -> cont.resumeWithException(Exception(error.message)) },
            onSuccess = { offerings -> cont.resume(offerings) },
          )
        }
      val pkg = offerings.current?.availablePackages?.find { it.identifier == packageId }
      if (pkg != null) {
        val transaction =
          suspendCancellableCoroutine { cont ->
            Purchases.sharedInstance.purchase(
              packageToPurchase = pkg,
              onError = {
                  error,
                  _,
                ->
                cont.resumeWithException(Exception(error.message))
              },
              onSuccess = { transaction, _ -> cont.resume(transaction) },
            )
          }
        emit(Result.success(transaction))
      } else {
        emit(Result.failure(IllegalStateException("Package not found: $packageId")))
      }
    } catch (e: Exception) {
      emit(Result.failure(e))
    }
  }.flowOn(Dispatchers.IO)
}
