package com.app.mqttchat.data.model.general

sealed interface ApiResult<out response> {
  data object Loading: ApiResult<Nothing>

  data class Success<out response>(val data: response): ApiResult<response>

  data class Error(val cause: Throwable): ApiResult<Nothing>
}