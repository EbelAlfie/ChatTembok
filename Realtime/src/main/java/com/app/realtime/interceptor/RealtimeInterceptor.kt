package com.app.realtime.interceptor

import com.app.realtime.model.mqtt.Connect
import com.app.realtime.model.mqtt.ConnectAck
import com.app.realtime.model.mqtt.Publish
import com.app.realtime.model.mqtt.PublishAck
import com.app.realtime.model.mqtt.PublishComp
import com.app.realtime.model.mqtt.PublishRec
import com.app.realtime.model.mqtt.PublishRel

interface RealtimeInterceptor {
  fun onConnect(connect: Connect)

  fun onConnectAck(connectAck: ConnectAck)

  fun onDisconnect()

  fun onSubscribe()

  fun onSubscribeAck(onSubscribeAck: Any?)

  fun onMessageReceived()

  /** Called when a server sent a Publish message with QoS 1.*/
  fun onPublish(publish: Publish)

  /** Called when a server sent a PubAck message for a Publish message with QoS 1.*/
  fun onPublishAck(publishAck: PublishAck)

  /** Called when a server sent a PubRel message for a Publish message with QoS 2.*/
  fun onPublishRel(publishRel: PublishRel)

  /** Called when a server sent a PubRec message for a Publish message with QoS 2.*/
  fun onPublishRec(publishRec: PublishRec)

  /**Called when a server sent a PubComp message for a Publish message with QoS 2.*/
  fun onPublishComp(publishComp: PublishComp)
}