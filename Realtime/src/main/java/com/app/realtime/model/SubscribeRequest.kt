package com.app.realtime.model

import com.app.realtime.Qos

data class SubscribeRequest(
  val topic: String,
  val qos: Qos
)
