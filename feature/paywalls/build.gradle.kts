plugins {
  id("catpaywalls.kmp.feature")
}

android {
  namespace = "com.revenuecat.catpaywalls.feature.paywalls"
}

kotlin {
  sourceSets {
    commonMain.dependencies {
      implementation(libs.purchases.kmp.ui)
    }
  }
}
