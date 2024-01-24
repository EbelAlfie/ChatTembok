package com.example.chattembok.presentation.chatlist.base

sealed class AmityResult {
  data object Loading: AmityResult()
  data object Success: AmityResult()

  data class Error(val throwable: Throwable): AmityResult()
}