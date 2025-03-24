package com.app.chuckerrealtime

import com.app.realtime.interceptor.RealtimeInterceptor
import com.app.realtime.model.mqtt.Connect
import com.app.realtime.model.mqtt.ConnectAck
import com.app.realtime.model.mqtt.Publish
import com.app.realtime.model.mqtt.PublishAck
import com.app.realtime.model.mqtt.PublishComp
import com.app.realtime.model.mqtt.PublishRec
import com.app.realtime.model.mqtt.PublishRel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class MqttInterceptor: RealtimeInterceptor {

  @Inject
  lateinit var eventCollector: EventCollector

  init { setupSubscriber() }

  private fun setupSubscriber() {
    CoroutineScope(Dispatchers.IO).launch { eventCollector.observeNewMessage() }
  }

  override fun onConnect(connect: Connect) {

  }

  override fun onConnectAck(connectAck: ConnectAck) {

  }

  override fun onDisconnect() {

  }

  override fun onSubscribe() {
  }

  override fun onSubscribeAck(onSubscribeAck: Any?) {
  }

  override fun onMessageReceived() {
  }

  override fun onPublish(publish: Publish) {
//    eventCollector.onNewMessage()
  }

  override fun onPublishAck(publishAck: PublishAck) {
//    eventCollector.onNewMessage()
  }

  override fun onPublishRel(publishRel: PublishRel) {
//    eventCollector.onNewMessage()
  }

  override fun onPublishRec(publishRec: PublishRec) {
//    eventCollector.onNewMessage()
  }

  override fun onPublishComp(publishComp: PublishComp) {
//    eventCollector.onNewMessage()
  }

}