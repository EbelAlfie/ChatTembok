package com.app.mqttchat.presentation.login

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(): ViewModel() {
  private val _loginState = MutableStateFlow(LoginUiState())
  val loginState = _loginState.asStateFlow()

  fun onNameChange(newName: String) =
    _loginState.update { it.copy(username = newName) }

  fun onPasswordChange(password: String) =
    _loginState.update { it.copy(password = password) }

  fun login() {
    validateInputs()
  }

  private fun validateInputs() {

  }
}