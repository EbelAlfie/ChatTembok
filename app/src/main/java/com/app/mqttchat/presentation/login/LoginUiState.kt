package com.app.mqttchat.presentation.login

data class LoginUiState(
  val username: String = "",
  val password: String = "",

  val usernameErrorMessage: String = "",
  val passwordErrorMessage: String = ""
)