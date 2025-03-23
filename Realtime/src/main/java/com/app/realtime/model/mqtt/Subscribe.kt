package com.app.realtime.model.mqtt

import com.app.realtime.config.Qos
import java.nio.ByteBuffer

data class Subscribe(
  val topic: String,
  val message: ByteBuffer,
  val qos: Qos,
  val retained: Boolean = true
)

data class SubscribeAck(
  val topic: String,
  val message: ByteBuffer,
  val qos: Qos,
  val retained: Boolean = true
)
