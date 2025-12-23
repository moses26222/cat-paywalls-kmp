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
package com.revenuecat.catpaywalls.core.designsystem.theme

import androidx.compose.runtime.Immutable
import androidx.compose.ui.graphics.Color

@Immutable
data class CatArticleColors(
  val primary: Color,
  val background: Color,
  val backgroundLight: Color,
  val backgroundDark: Color,
  val absoluteWhite: Color,
  val absoluteBlack: Color,
  val white: Color,
  val white12: Color,
  val white56: Color,
  val white70: Color,
  val black: Color,
) {
  companion object {
    // Primary color (same for both themes)
    private val colorPrimary = Color(0xFFF2545B)

    // Light theme colors
    private val backgroundLight = Color(0xFFF4EBF4)
    private val background800Light = Color(0xFFD0C7C7)
    private val background900Light = Color(0xFFFFFFFF)
    private val whiteColor = Color(0xFFFFFFFF)
    private val blackColor = Color(0xFF000000)
    private val white12Light = Color(0x1F000000)
    private val white56Light = Color(0x8F000000)
    private val white70Light = Color(0xB3000000)

    // Dark theme colors
    private val backgroundDark = Color(0xFF2B292B)
    private val background800Dark = Color(0xFF424242)
    private val background900Dark = Color(0xFF212121)
    private val white12Dark = Color(0xFFFAFAFA)
    private val white56Dark = Color(0x8EFFFFFF)
    private val white70Dark = Color(0xB2FFFFFF)

    // In dark mode, "white" becomes black (for text that should be dark on light bg)
    private val whiteDark = Color(0xFF000000)

    // In dark mode, "black" becomes white (for text that should be light on dark bg)
    private val blackDark = Color(0xFFFFFFFF)

    /**
     * Provides the default colors for the dark mode of the app.
     *
     * @return A [CatArticleColors] instance holding our color palette.
     */
    fun defaultDarkColors(): CatArticleColors = CatArticleColors(
      primary = colorPrimary,
      background = backgroundDark,
      backgroundLight = background800Dark,
      backgroundDark = background900Dark,
      absoluteWhite = whiteColor,
      absoluteBlack = blackColor,
      white = whiteDark,
      white12 = white12Dark,
      white56 = white56Dark,
      white70 = white70Dark,
      black = blackDark,
    )

    /**
     * Provides the default colors for the light mode of the app.
     *
     * @return A [CatArticleColors] instance holding our color palette.
     */
    fun defaultLightColors(): CatArticleColors = CatArticleColors(
      primary = colorPrimary,
      background = backgroundLight,
      backgroundLight = background800Light,
      backgroundDark = background900Light,
      absoluteWhite = whiteColor,
      absoluteBlack = blackColor,
      white = whiteColor,
      white12 = white12Light,
      white56 = white56Light,
      white70 = white70Light,
      black = blackColor,
    )
  }
}
