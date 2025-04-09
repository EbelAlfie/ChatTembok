package com.app.chat.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

sealed class ChatEvent(
  @Expose
  @SerializedName("event")
  val event: String
) {

  data class OnlineEvent(
    @Expose
    @SerializedName("user_id")
    val userId: String,
    @Expose
    @SerializedName("timestamp")
    val timestamp: String
  ): ChatEvent("user.online")

  data class OfflineEvent(
    @Expose
    @SerializedName("user_id")
    val userId: String,
    @Expose
    @SerializedName("timestamp")
    val timestamp: String
  ): ChatEvent("user.offline")

  data class ChannelAddedEvent(
    @Expose
    @SerializedName("channel_id")
    val channelId: String,
    @Expose
    @SerializedName("user_id")
    val userId: String,
    @Expose
    @SerializedName("role")
    val role: String,
    @Expose
    @SerializedName("timestamp")
    val timestamp: String
  ): ChatEvent("channel.member.added")

  data class MessageSentEvent(
    @Expose
    @SerializedName("message_id")
    val messageId: String,
    @Expose
    @SerializedName("channel_id")
    val channelId: String,
    @Expose
    @SerializedName("sender_id")
    val senderId: String,
    @Expose
    @SerializedName("text")
    val text: String,
    @Expose
    @SerializedName("attachments")
    val attachments: List<String>
  ): ChatEvent("message.sent")

  data class TypingStart(
    @Expose
    @SerializedName("channel_id")
    val channelId: String,
    @Expose
    @SerializedName("user_id")
    val userId: String,
    @Expose
    @SerializedName("timestamp")
    val timestamp: String
  ): ChatEvent("typing.start")

  data class TypingStop(
    @Expose
    @SerializedName("channel_id")
    val channelId: String,
    @Expose
    @SerializedName("user_id")
    val userId: String,
    @Expose
    @SerializedName("timestamp")
    val timestamp: String
  ): ChatEvent("typing.stop")

}