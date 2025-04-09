package com.app.mqttchat.data.model

import com.app.mqttchat.domain.model.ChatMessageModel
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class MessageResponse(
  @Expose
  @SerializedName("messageId")
  val id: String?,
  @Expose
  @SerializedName("user")
  val user: UserResponse?,
  @Expose
  @SerializedName("text")
  val text: String?
) {
  companion object {
    fun transform(event: MessageResponse): ChatMessageModel {
      return ChatMessageModel(
        id = event.id ?: "",
        user = UserResponse.transform(event.user),
        text = event.text ?: ""
      )
    }
  }
}