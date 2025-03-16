package com.app.mqttchat.presentation.common

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

abstract class ComposeViewModel<uiState>: ViewModel() {
  private val _uiState: MutableStateFlow<UiState<uiState>> by lazy { MutableStateFlow(initialState()) }
  val uiState = _uiState.asStateFlow()

  abstract fun initialState(): UiState<uiState>

  fun updateState(newState: UiState<uiState>.() -> UiState<uiState>) {
    _uiState.update { it.newState() }
  }

}