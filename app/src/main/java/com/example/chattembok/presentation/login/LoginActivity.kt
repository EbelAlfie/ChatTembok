package com.example.chattembok.presentation.login

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.chatrawatinapp.databinding.ActivityLoginBinding
import com.example.chattembok.backend.UserDataModel
import com.example.chattembok.presentation.login.model.LoginModel
import com.example.chattembok.presentation.orderhistory.OrderHistoryActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginActivity: AppCompatActivity() {

  private val viewModel: LoginViewModel by viewModels()
  private val binding: ActivityLoginBinding by lazy {
    ActivityLoginBinding.inflate(layoutInflater)
  }

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(binding.root)
    setupViews()
  }

  private fun setupViews() {
    binding.apply {
      btnSubmit.setOnClickListener {
        validateInput()
      }
    }
  }

  private fun validateInput() {
    binding.run {
      val name = tieUsername.text.toString()
      val pass = tiePassword.text.toString()
      if (name.isBlank()) return
      if (pass.isBlank()) return
      viewModel.validateLogin(LoginModel(name, pass)).let {
        if (it != null) gotoOrderHistoryActivity(it)
      }
    }
  }

  private fun gotoOrderHistoryActivity(data: UserDataModel) {
    startActivity(OrderHistoryActivity.newIntent(this, data))
  }
}