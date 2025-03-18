package com.app.mqttchat.presentation.common

sealed interface UiState<out dataType> {
  data object Loading: UiState<Nothing>

  data class Loaded<dataType>(val data: dataType): UiState<dataType>

  data class Error(val error: Throwable): UiState<Nothing>
}
