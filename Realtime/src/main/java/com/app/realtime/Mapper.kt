package com.app.realtime

import com.app.realtime.config.Qos
import com.app.realtime.model.mqtt.Publish
import com.app.realtime.model.mqtt.PublishAck
import com.hivemq.client.mqtt.mqtt5.message.publish.Mqtt5Publish
import com.hivemq.client.mqtt.mqtt5.message.publish.puback.Mqtt5PubAck

object Mapper {
  fun toPublish(data: Mqtt5Publish) = Publish(
    topic = data.topic.levels.joinToString(),
    message = data.payload,
    qos = Qos.fromCode(data.qos.code),
    retained = data.isRetain,
  )

  fun toPublishAck(publish: Mqtt5Publish, ack: Mqtt5PubAck) = PublishAck(
    reasonCode = ack.reasonCode.code,
    reasonMessage = ack.reasonString.toString(),
    userProp = "", //TODO
    publish = toPublish(publish)
  )


}