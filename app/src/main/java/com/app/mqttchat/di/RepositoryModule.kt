package com.app.mqttchat.di

import com.app.mqttchat.data.repository.ApplicationRepositoryImpl
import com.app.mqttchat.data.repository.ChatRepositoryImpl
import com.app.mqttchat.domain.repository.ApplicationRepository
import com.app.mqttchat.domain.repository.ChatRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface RepositoryModule {
  @Binds
  fun provideChatRepository(chatRepository: ChatRepositoryImpl): ChatRepository
  @Binds
  fun provideAppRepository(appRepository: ApplicationRepositoryImpl): ApplicationRepository
}