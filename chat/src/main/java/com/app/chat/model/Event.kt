package com.app.chat.model

import com.google.gson.annotations.SerializedName

data class ChatEvent(
  @SerializedName("type")
  val type: String
) {
}