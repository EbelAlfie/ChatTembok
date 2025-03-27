package com.app.mqttchat.di

import com.app.realtime.RealtimeClient
import com.app.realtime.config.ConnectionConfig
import com.app.realtime.interceptor.RealtimeInterceptor
import com.app.realtime.model.mqtt.Connect
import com.app.realtime.model.mqtt.ConnectAck
import com.app.realtime.model.mqtt.Publish
import com.app.realtime.model.mqtt.PublishAck
import com.app.realtime.model.mqtt.PublishComp
import com.app.realtime.model.mqtt.PublishRec
import com.app.realtime.model.mqtt.PublishRel
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RealtimeModule {

  @Provides
  @Singleton
  fun provideRealtimeClient(): RealtimeClient {
    val basicInterceptor = object: RealtimeInterceptor {
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
      }

      override fun onPublishAck(publishAck: PublishAck) {
      }

      override fun onPublishRel(publishRel: PublishRel) {
      }

      override fun onPublishRec(publishRec: PublishRec) {
      }

      override fun onPublishComp(publishComp: PublishComp) {
      }
    }
    return RealtimeClient
      .Builder()
      .addMqttInterceptor(basicInterceptor)
      .setConnectionConfig(ConnectionConfig.defaultMqttConfig())
      .buildAsMqtt()
  }
}