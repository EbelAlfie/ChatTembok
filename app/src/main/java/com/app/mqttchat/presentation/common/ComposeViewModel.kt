package com.app.mqttchat.presentation.common

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

abstract class ComposeViewModel<uiState>: ViewModel() {
  protected val _uiState: MutableStateFlow<uiState> by lazy { MutableStateFlow(initialState()) }
  val uiState = _uiState.asStateFlow()

  abstract fun initialState(): uiState

  fun updateState(newState: uiState.() -> uiState) {
    _uiState.update { it.newState() }
  }

}