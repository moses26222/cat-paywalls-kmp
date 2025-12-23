plugins {
  id("catpaywalls.kmp.library")
  alias(libs.plugins.kotlin.serialization)
}

android {
  namespace = "com.revenuecat.catpaywalls.core.model"
}

kotlin {
  sourceSets {
    commonMain.dependencies {
      api(libs.kotlinx.serialization.json)
      compileOnly("androidx.compose.runtime:runtime-annotation:1.9.0")
    }
  }
}
