package com.example.chattembok.presentation.chatlist

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

class ChatActivity: AppCompatActivity() {

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)

  }
  companion object {
    fun newIntent(from: Context): Intent {
      return Intent(from, ChatActivity::class.java)
    }
  }
}