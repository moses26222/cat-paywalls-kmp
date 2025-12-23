plugins {
  id("catpaywalls.kmp.library.compose")
  alias(libs.plugins.kotlin.serialization)
}

android {
  namespace = "com.revenuecat.catpaywalls.core.navigation"
}

kotlin {
  sourceSets {
    commonMain.dependencies {
      implementation(projects.core.model)

      api(libs.navigation.compose)
      api(libs.kotlinx.serialization.json)
    }
  }
}
