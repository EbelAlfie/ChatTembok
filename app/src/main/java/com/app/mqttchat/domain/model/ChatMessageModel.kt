package com.app.mqttchat.domain.model

import java.util.UUID

data class ChatMessageModel(
  val id: String = UUID.randomUUID().toString(),
  val user: UserModel,
  val text: String
)
