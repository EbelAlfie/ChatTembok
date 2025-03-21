package com.app.chuckerrealtime.data.source

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class RoomModule {
  @Provides
  fun provideRoom() {

  }
}