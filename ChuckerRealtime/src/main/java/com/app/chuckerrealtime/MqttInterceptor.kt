package com.app.chuckerrealtime

import android.content.Context
import com.app.chuckerrealtime.data.InterceptorRepositoryImpl
import com.app.chuckerrealtime.data.service.InterceptorService
import com.app.chuckerrealtime.di.RoomModule
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

class MqttInterceptor(context: Context): RealtimeInterceptor {
  private val localInterceptService: InterceptorService = RoomModule().provideDatabase(context).interceptorService()
  private val repository = InterceptorRepositoryImpl(localInterceptService)

  private val eventCollector: EventCollector = EventCollector(context, repository)

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