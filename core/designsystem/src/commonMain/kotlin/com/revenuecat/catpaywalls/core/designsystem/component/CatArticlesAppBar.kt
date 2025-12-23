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
package com.revenuecat.catpaywalls.core.designsystem.component

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.revenuecat.catpaywalls.core.designsystem.theme.CatArticlesTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CatArticlesAppBar(
  modifier: Modifier = Modifier,
  title: String = "Cat Articles",
  navigationIcon: @Composable () -> Unit = {},
  actions: @Composable () -> Unit = {},
) {
  TopAppBar(
    modifier = modifier,
    title = {
      Text(
        text = title,
        color = CatArticlesTheme.colors.absoluteWhite,
        fontSize = 18.sp,
        fontWeight = FontWeight.Bold,
      )
    },
    navigationIcon = navigationIcon,
    actions = { actions() },
    colors = TopAppBarDefaults.topAppBarColors().copy(
      containerColor = Color.Transparent,
    ),
  )
}
