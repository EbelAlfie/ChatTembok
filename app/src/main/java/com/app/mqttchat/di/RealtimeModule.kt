package com.app.mqttchat.di

import com.app.realtime.RealtimeClient
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class RealtimeModule {
  @Provides
  fun provideRealtimeClient(): RealtimeClient {
    return RealtimeClient
      .Builder()
      .build()
  }
}