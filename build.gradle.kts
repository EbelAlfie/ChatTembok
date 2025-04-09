// Top-level build file where you can add configuration options common to all sub-projects/modules.
buildscript {
  dependencies {
  }
}

plugins {
  id("com.google.devtools.ksp") version "2.0.21-1.0.27" apply false
  alias(libs.plugins.android.application) apply false
  alias(libs.plugins.kotlin.android) apply false
  alias(libs.plugins.dagger.hilt.plugin) apply false
  alias(libs.plugins.compose.compiler) apply false
  alias(libs.plugins.android.library) apply false
  alias(libs.plugins.jetbrains.kotlin.jvm) apply false
}