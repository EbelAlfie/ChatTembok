package com.example.chattembok.presentation.login

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.chattembok.databinding.ActivityLoginBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginActivity: AppCompatActivity() {

  private val viewModel: LoginViewModel by viewModels()
  private val binding: ActivityLoginBinding =
    ActivityLoginBinding.inflate(layoutInflater)
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(binding.root)
    
  }
}