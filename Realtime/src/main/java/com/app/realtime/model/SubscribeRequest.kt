package com.app.realtime.model

import com.app.realtime.config.Qos

data class SubscribeRequest(
  val topic: String,
  val qos: Qos,
) {
  companion object {
    fun defaultSubRequest(topic: String) = SubscribeRequest(
      topic = topic,
      qos = Qos.AT_MOST_ONCE
    )
  }
}
