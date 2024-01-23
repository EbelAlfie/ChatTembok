// Top-level build file where you can add configuration options common to all sub-projects/modules.
buildscript {
  repositories {
    google()
    gradlePluginPortal()
  }
  dependencies {
    classpath("gradle.plugin.com.github.sgtsilvio.gradle:android-retrofix:0.4.1")
  }
}

plugins {
  id("com.android.application") version "8.2.0" apply false
  id("org.jetbrains.kotlin.android") version "1.9.10" apply false
}