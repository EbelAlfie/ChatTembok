package com.app.realtime.model

import com.app.realtime.config.Qos

data class PublishRequest(
  val topic: String,
  val message: String,
  val qos: Qos,
  val retained: Boolean = true
) {
  companion object {
    fun defaultPubRequest(topic: String, message: String) =
      PublishRequest(
        topic = topic,
        message = message,
        qos = Qos.AT_MOST_ONCE
      )
  }
}