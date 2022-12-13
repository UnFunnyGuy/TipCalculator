
plugins {
  id("com.android.application")
  id("org.jetbrains.kotlin.android")
  id("org.jetbrains.kotlin.kapt")
  id("dagger.hilt.android.plugin")
}

android {
  namespace = "com.unfunny.tip"
  compileSdk = 33

  defaultConfig {
    applicationId = "com.unfunny.tip"
    minSdk = 29
    targetSdk = 33
    versionCode = 1
    versionName = "1.0"

    testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    vectorDrawables { useSupportLibrary = true }
  }

  buildTypes {
    getByName("release") {
      isMinifyEnabled = true
      proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
    }
  }
  compileOptions {
    sourceCompatibility = JavaVersion.VERSION_1_8
    targetCompatibility = JavaVersion.VERSION_1_8
  }
  kotlinOptions { jvmTarget = JavaVersion.VERSION_1_8.toString() }
  buildFeatures { compose = true }
  composeOptions {
    kotlinCompilerExtensionVersion =
        extensions
            .getByType<VersionCatalogsExtension>()
            .named("libs")
            .findVersion("androidxComposeCompiler")
            .get()
            .toString()
  }
  packagingOptions { resources { excludes += "/META-INF/{AL2.0,LGPL2.1}" } }
}

dependencies {
  implementation(libs.androidx.activity.compose)
  implementation(libs.androidx.compose.ui)
  implementation(libs.androidx.constraintlayout)
  implementation(libs.androidx.compose.ui.graphics)
  implementation(libs.androidx.compose.ui.tooling.preview)
  implementation (libs.google.material)
  implementation(libs.androidx.compose.material3)
  implementation(libs.androidx.compose.material.icons.full)

  implementation(libs.androidx.dataStore.preferences)

  implementation(libs.hilt.android)
  implementation(libs.hilt.navigationCompose)
  kapt(libs.hilt.compiler)

  implementation(libs.accompanist.systemuicontroller)

  testImplementation(libs.junit4)
  androidTestImplementation(libs.androidx.test.ext)
  androidTestImplementation(libs.androidx.test.espresso.core)
  androidTestImplementation(libs.androidx.compose.ui.test)
  debugImplementation(libs.androidx.compose.ui.tooling)
  debugRuntimeOnly(libs.androidx.compose.ui.testManifest)
}
