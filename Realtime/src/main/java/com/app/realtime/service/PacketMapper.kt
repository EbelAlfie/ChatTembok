package com.app.realtime.service

import com.app.realtime.config.Qos
import com.app.realtime.model.mqtt.Publish
import com.app.realtime.model.mqtt.PublishAck
import com.app.realtime.model.mqtt.PublishComp
import com.app.realtime.model.mqtt.PublishRec
import com.app.realtime.model.mqtt.PublishRel
import com.app.realtime.model.mqtt.SubscribeAck
import com.hivemq.client.mqtt.mqtt5.message.publish.Mqtt5Publish
import com.hivemq.client.mqtt.mqtt5.message.publish.puback.Mqtt5PubAck
import com.hivemq.client.mqtt.mqtt5.message.publish.pubcomp.Mqtt5PubComp
import com.hivemq.client.mqtt.mqtt5.message.publish.pubrec.Mqtt5PubRec
import com.hivemq.client.mqtt.mqtt5.message.publish.pubrel.Mqtt5PubRel
import com.hivemq.client.mqtt.mqtt5.message.subscribe.suback.Mqtt5SubAck

object PacketMapper {
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

  fun toPublishRel(rel: Mqtt5PubRel) = PublishRel(
    reasonCode = rel.reasonCode.code,
    reasonMessage = rel.reasonString.toString(),
    userProp = "", //TODO
  )

  fun toPublishRec(publish: Mqtt5Publish, rec: Mqtt5PubRec) = PublishRec(
    reasonCode = rec.reasonCode.code,
    reasonMessage = rec.reasonString.toString(),
    userProp = "", //TODO
    publish = toPublish(publish)
  )

  fun toPublishComp(pubRel: Mqtt5PubRel, pubComp: Mqtt5PubComp) = PublishComp(
    reasonCode = pubComp.reasonCode.code,
    reasonMessage = pubComp.reasonString.toString(),
    userProp = "", //TODO,
    publishRel = toPublishRel(pubRel)
  )

  fun onSubscribeAck(subAck: Mqtt5SubAck) = SubscribeAck(
    reasonCodes = subAck.reasonCodes.map { it.code },
    reasonMessage = subAck.reasonString.toString(),
    userProp = "", //TODO,
  )

}