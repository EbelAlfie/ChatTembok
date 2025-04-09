package com.app.mqttchat.presentation.login

import com.app.mqttchat.presentation.login.component.Network

data class LoginUiState(
  val username: String = "",
  val password: String = "",
  val network: Network = Network.MQTT,
  val host: String = "10.4.77.103:1883",

  val usernameErrorMessage: String = "",
  val passwordErrorMessage: String = ""
)