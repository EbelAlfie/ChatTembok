package com.app.mqttchat.presentation.login

import android.content.Context
import android.content.SharedPreferences
import androidx.lifecycle.ViewModel
import com.app.mqttchat.App
import com.app.mqttchat.domain.model.UserModel
import com.app.mqttchat.presentation.common.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(): ViewModel() {
  private val _loginState = MutableStateFlow(LoginUiState())
  val loginState = _loginState.asStateFlow()

  private val _apiState = MutableStateFlow<UiState<UserModel>?>(null)
  val apiState = _apiState.asStateFlow()

  fun onNameChange(newName: String) =
    _loginState.update { it.copy(username = newName) }

  fun onPasswordChange(password: String) =
    _loginState.update { it.copy(password = password) }

  fun login() {
    val loginData = _loginState.value

    if (loginData.username.isBlank()) return
    if (loginData.password.isBlank()) return

    onLoginSuccess()
  }

  private fun onLoginFailed() {

  }

  private fun onLoginSuccess() {
    val userData = _loginState.value
    val user = UserModel(
      id = userData.password,
      username = userData.username
    )

    App.setUser(user)

    _apiState.value = UiState.Loaded(user)
  }

}