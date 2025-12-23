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

import com.revenuecat.catpaywalls.core.model.Article
import com.revenuecat.catpaywalls.core.network.CatArticlesService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

internal class ArticlesRepositoryImpl(private val catArticlesService: CatArticlesService) : ArticlesRepository {
  override fun fetchArticles(): Flow<Result<List<Article>>> = flow {
    val result = catArticlesService.fetchArticles()
    emit(result)
  }.flowOn(Dispatchers.IO)

  override fun getArticleById(id: Long): Flow<Result<Article>> = flow {
    val result = catArticlesService.fetchArticles()
    val article = result.getOrNull()?.find { it.id == id }
    if (article != null) {
      emit(Result.success(article))
    } else {
      emit(Result.failure(NoSuchElementException("Article with id $id not found")))
    }
  }.flowOn(Dispatchers.IO)
}
