package com.app.chuckerrealtime.activity

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import com.app.chuckerrealtime.activity.component.InterceptorScreen

class InterceptorActivity: AppCompatActivity() {

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContent {
      InterceptorScreen()
    }
  }
}