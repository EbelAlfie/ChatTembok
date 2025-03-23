package com.app.realtime.interceptor

import com.app.realtime.model.mqtt.Publish
import com.app.realtime.model.mqtt.PublishAck

interface RealtimeInterceptor {
  fun onConnect()

  fun onDisconnect()

  /**Called when a server sent a Publish message with QoS 1.*/
  fun onPublish(publishAck: Publish)

  /**Called when a server sent a PubAck message for a Publish message with QoS 1.*/
  fun onPublishAck(publishAck: PublishAck)

  fun onSubscribed()

  fun onMessageReceived()
}