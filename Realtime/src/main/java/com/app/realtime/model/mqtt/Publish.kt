package com.app.realtime.model.mqtt

import com.app.realtime.config.Qos
import java.nio.ByteBuffer
import java.util.Optional

data class Publish(
  val topic: String,
  val message: Optional<ByteBuffer>,
  val qos: Qos,
  val retained: Boolean = true
)

data class PublishAck(
  val reasonCode: Int,
  val reasonMessage: String,
  val userProp: String,
  val publish: Publish
)