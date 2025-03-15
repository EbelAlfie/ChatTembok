package com.app.mqttchat.presentation.common

sealed interface UiState<out data> {
  data object Loading: UiState<Nothing>

  data class Loaded<data>(val state: data): UiState<data>

  data class Error(val error: Throwable): UiState<Nothing>
}
