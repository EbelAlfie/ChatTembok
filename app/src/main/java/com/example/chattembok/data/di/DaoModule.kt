package com.example.chattembok.data.di

import com.example.chattembok.backend.monggodb.monggoo.MonggoDB
import com.example.chattembok.backend.monggodb.monggoo.MonggoDbModule
import com.example.chattembok.backend.monggodb.operation.ChatRoomDao
import com.example.chattembok.backend.monggodb.operation.OrderDbDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class DaoModule {
  @Provides
  fun provideOrderDao(monggoDb: MonggoDB): OrderDbDao {
    return monggoDb.orderDao()
  }

  @Provides
  fun provideChatDao(monggoDb: MonggoDB): ChatRoomDao {
    return monggoDb.chatDao()
  }
}