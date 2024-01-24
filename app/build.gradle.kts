import java.io.FileInputStream
import java.util.Properties

val localProperty = Properties().apply {
  load(FileInputStream(File(rootProject.rootDir, "local.properties")))
}

plugins {
  kotlin("kapt")
  id("com.android.application")
  id("org.jetbrains.kotlin.android")
  id("com.google.dagger.hilt.android")
  id("kotlin-parcelize")
}

android {
  namespace = "com.example.chatrawatinapp"
  compileSdk = 34

  defaultConfig {
    applicationId = "com.example.chatrawatinapp"
    minSdk = 21
    targetSdk = 34
    versionCode = 1
    versionName = "1.0"

    testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"

    buildConfigField("String", "AMITY_KEY", localProperty.getProperty("amity.kunci"))
  }

  buildTypes {

    debug {
      resValue ("bool", "IS_HIDDEN_AMITY_LOG", "false")
    }

    release {
      isMinifyEnabled = false
      proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
    }
  }
  compileOptions {
    sourceCompatibility = JavaVersion.VERSION_1_8
    targetCompatibility = JavaVersion.VERSION_1_8
  }
  kotlinOptions {
    jvmTarget = "1.8"
  }
  buildFeatures {
    viewBinding = true
    buildConfig = true
  }

  packaging {
    resources {
      excludes += "/META-INF/{AL2.0,LGPL2.1}"
      excludes += "META-INF/INDEX.LIST"
      excludes += "META-INF/io.netty.versions.properties"
    }
  }
}

dependencies {
  implementation("com.github.AmityCo.Amity-Social-Cloud-UIKit-Android:amity-uikit:3.15.0")
  implementation("com.github.AmityCo.Amity-Social-Cloud-SDK-Android:amity-sdk:6.25.1")

  //MONGGO DB
  implementation ("androidx.room:room-runtime:2.5.1")
  kapt ("androidx.room:room-compiler:2.5.1")
  implementation ("androidx.room:room-ktx:2.5.1")

  //Dagger hilt
  implementation("com.google.dagger:hilt-android:2.48")
  kapt("com.google.dagger:hilt-android-compiler:2.48")

  implementation("androidx.core:core-ktx:1.6.0")
  implementation ("androidx.fragment:fragment-ktx:1.5.6")
  implementation("androidx.appcompat:appcompat:1.4.2")
  implementation("com.google.android.material:material:1.6.1")
  testImplementation("junit:junit:4.13.2")
  androidTestImplementation("androidx.test.ext:junit:1.1.5")
  androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
}