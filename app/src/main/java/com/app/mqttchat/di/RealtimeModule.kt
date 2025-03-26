package com.app.mqttchat.di

import com.app.realtime.RealtimeClient
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
    return RealtimeClient
      .Builder()
//      .addMqttInterceptor(MqttInterceptor())
      .build()
  }
}