package com.example.chattembok.data.di

import com.example.chattembok.data.repository.chat.ChatRepository
import com.example.chattembok.data.repository.chat.ChatRepositoryImpl
import com.example.chattembok.data.repository.order.OrderRepository
import com.example.chattembok.data.repository.order.OrderRepositoryImpl
import com.example.chattembok.data.repository.user.UserRepository
import com.example.chattembok.data.repository.user.UserRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface RepoModule {
  @Binds
  fun provideUserRepo (repo: UserRepositoryImpl): UserRepository

  @Binds
  fun provideOrderRepo(repo: OrderRepositoryImpl): OrderRepository

  @Binds
  fun provideChatRepo(repo: ChatRepositoryImpl): ChatRepository
}