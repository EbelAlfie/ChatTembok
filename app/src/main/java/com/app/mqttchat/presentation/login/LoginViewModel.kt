package com.app.mqttchat.presentation.login

import androidx.lifecycle.ViewModel
import com.app.mqttchat.presentation.common.ComposeViewModel
import com.app.mqttchat.presentation.common.UiState
import dagger.hilt.android.lifecycle.HiltViewModel

@HiltViewModel
class LoginViewModel: ComposeViewModel<LoginUiState>() {

  override fun initialState(): UiState<LoginUiState> = UiState.Loaded(LoginUiState())

  fun onNameChange(newName: String) {
    updateState {
      (this as? UiState.Loaded)?.copy(state.copy(username = newName)) ?: this
    }
  }

  fun onPasswordChange(value: String) {
    updateState {
      (this as? UiState.Loaded)?.copy(state.copy(password = value)) ?: this
    }
  }

  fun login() {

  }

}