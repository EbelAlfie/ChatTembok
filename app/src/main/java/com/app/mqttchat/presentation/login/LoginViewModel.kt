package com.app.mqttchat.presentation.login

import androidx.lifecycle.ViewModel
import com.app.mqttchat.presentation.common.ComposeViewModel
import com.app.mqttchat.presentation.common.UiState
import dagger.hilt.android.lifecycle.HiltViewModel

@HiltViewModel
class LoginViewModel: ComposeViewModel<LoginUiState>() {

  override fun <uiState> initialState(): UiState<uiState> {

  }

  fun onNameChange() {

  }

  fun onPasswordChange() {

  }

  fun login() {

  }

}