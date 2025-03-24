plugins {
  alias(libs.plugins.android.library)
  alias(libs.plugins.kotlin.android)
  id("com.google.dagger.hilt.android")
  id("kotlin-kapt")
  id("kotlin-parcelize")
}

android {
  namespace = "com.app.realtime"
  compileSdk = 35

  defaultConfig {
    minSdk = 24

    testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    consumerProguardFiles("consumer-rules.pro")
  }

  buildTypes {
    release {
      isMinifyEnabled = false
      proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
    }
  }
  compileOptions {
    sourceCompatibility = JavaVersion.VERSION_11
    targetCompatibility = JavaVersion.VERSION_11
  }
  kotlinOptions {
    jvmTarget = "11"
  }
}

dependencies {
  implementation(project(":core"))

  implementation(libs.dagger.hilt)
  kapt(libs.dagger.hilt.compiler)

  implementation(libs.gson)

  implementation(libs.okhttp)
  implementation(platform(libs.okhttp3.okhttp.bom))

  implementation(libs.hivemq.mqtt.client)
  //  implementation(libs.hivemq.mqtt.client.websocket)

  implementation(libs.androidx.core.ktx)
  implementation(libs.androidx.appcompat)
  implementation(libs.material)
  testImplementation(libs.junit)
  androidTestImplementation(libs.androidx.junit)
  androidTestImplementation(libs.androidx.espresso.core)
}