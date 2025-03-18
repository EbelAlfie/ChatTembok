package com.app.mqttchat.domain.model

data class ChatItem (
  val roomId: String = "",
  val title: String = "",
  val avatar: String = "",
  val subtitle: String = ""
)
