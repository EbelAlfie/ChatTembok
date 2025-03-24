package com.app.realtime.model.mqtt

import com.app.realtime.config.Qos
import java.nio.ByteBuffer
import java.util.Optional

/**
 * Ack = Acknowledge to confirm message receipt between clients and the broker
 * Rec = Received Sent by the receiver (broker or subscriber) to the sender (publisher) to acknowledge receipt of a QoS 2 PUBLISH packet.
 * Rel = Release Sent by the sender to the receiver to acknowledge the receipt of the PUBREC packet.
 * Comp = Complete Sent by the receiver to the sender to indicate that the message delivery process is complete.
 */

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

data class PublishRel(
  val reasonCode: Int,
  val reasonMessage: String,
  val userProp: String
)

data class PublishRec(
  val reasonCode: Int,
  val reasonMessage: String,
  val userProp: String,
  val publish: Publish
)

data class PublishComp(
  val reasonCode: Int,
  val reasonMessage: String,
  val userProp: String,
  val publishRel: PublishRel
)