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
package com.revenuecat.catpaywalls.feature.article

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.revenuecat.catpaywalls.core.data.ArticlesRepository
import com.revenuecat.catpaywalls.core.data.PaywallsRepository
import com.revenuecat.catpaywalls.core.model.Article
import com.revenuecat.purchases.kmp.models.CustomerInfo
import dev.zacsweers.metro.Inject
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn

class CatArticlesDetailViewModel(
  articleId: Long,
  articlesRepository: ArticlesRepository,
  paywallsRepository: PaywallsRepository,
) : ViewModel() {
  val article: StateFlow<Article?> =
    articlesRepository
      .getArticleById(articleId)
      .map { result ->
        result.getOrNull()
      }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = null,
      )

  val customerInfo: StateFlow<CustomerInfo?> =
    paywallsRepository
      .fetchCustomerInfo()
      .map { result ->
        result.getOrNull()
      }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = null,
      )

  @Inject
  class Factory(
    private val articlesRepository: ArticlesRepository,
    private val paywallsRepository: PaywallsRepository,
  ) {
    fun create(articleId: Long): CatArticlesDetailViewModel = CatArticlesDetailViewModel(
      articleId = articleId,
      articlesRepository = articlesRepository,
      paywallsRepository = paywallsRepository,
    )
  }
}
