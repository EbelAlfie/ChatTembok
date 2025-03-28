package com.app.mqttchat.domain.model

import com.app.mqttchat.data.model.RealtimeMessageEvent
import java.util.UUID

data class ChatMessageModel(
  val id: String = UUID.randomUUID().toString(),
  val user: UserModel,
  val text: String
) {

  companion object {

    fun toMessageRequest(chat: ChatMessageModel): RealtimeMessageEvent {
      return RealtimeMessageEvent(
        id = chat.id,
        user = UserModel.toUserResponse(chat.user),
        text = chat.text
      )
    }
  }
}
