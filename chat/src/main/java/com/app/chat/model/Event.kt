package com.app.chat.model

sealed class ChatEvent {
  abstract val type: String
}