package com.app.mqttchat.data.model

import com.app.mqttchat.domain.model.ChatMessageModel
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class MessageEvent(
  @Expose
  @SerializedName("id")
  val id: String,
  @Expose
  @SerializedName("user")
  val user: UserResponse,
  @Expose
  @SerializedName("text")
  val text: String
) {
  companion object {
    fun transform(event: MessageEvent): ChatMessageModel {
      return ChatMessageModel(
        id = event.id,
        user = UserResponse.transform(event.user),
        text = event.text
      )
    }
  }
}