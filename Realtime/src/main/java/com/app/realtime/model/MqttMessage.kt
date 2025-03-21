package com.app.realtime.model

import com.app.realtime.config.Qos
import java.nio.ByteBuffer

data class MqttMessage(
  val topic: String,
  val message: ByteBuffer,
  val qos: Qos = Qos.AT_MOST_ONCE,
  val retained: Boolean = true
) {

  fun asString() {

  }

}