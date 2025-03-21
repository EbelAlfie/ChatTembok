package com.app.mqttchat.di

import com.app.realtime.RealtimeClient
import com.app.realtime.api.RealtimeApiAdapter
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class RealtimeModule {
  @Provides
  fun provideRealtimeClient(
    client: RealtimeApiAdapter
  ): RealtimeClient {
    return RealtimeClient.Builder().build(client)
  }
}