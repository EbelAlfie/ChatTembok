package com.app.realtime.model

import com.app.realtime.config.Qos

data class PublishRequest<messageType>(
  val topic: String,
  val message: messageType,
  val qos: Qos,
  val retained: Boolean = false
) {
  companion object {
    fun <messageType>defaultPubRequest(topic: String, message: messageType) =
      PublishRequest(
        topic = topic,
        message = message,
        qos = Qos.EXACTLY_ONCE
      )
  }
}