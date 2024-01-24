package com.example.chattembok.presentation.chatlist.base

sealed class StatusHandler <out V> {
  data object Loading: StatusHandler<Nothing>()
  data class Success<V>(val data: V): StatusHandler<V>()

  data class Error(val error: Throwable): StatusHandler<Nothing>()
}