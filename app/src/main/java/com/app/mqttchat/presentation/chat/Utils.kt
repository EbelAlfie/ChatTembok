package com.app.mqttchat.presentation.chat

import com.app.mqttchat.domain.model.ChatMessageModel
import com.app.mqttchat.domain.model.UserModel

fun isMine(message: ChatMessageModel, me: UserModel?): Boolean {
  val sender = message.user
  return sender.id == me?.id
}
