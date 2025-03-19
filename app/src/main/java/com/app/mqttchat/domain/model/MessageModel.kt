package com.app.mqttchat.domain.model

import java.util.UUID

data class MessageModel(
  val messageId: String = UUID.randomUUID().toString(),
  val user: UserModel,
  val text: String
)
