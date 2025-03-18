package com.app.mqttchat.di

import com.app.mqttchat.domain.ApplicationUseCaseImpl
import com.app.mqttchat.domain.ChatUseCaseImpl
import com.app.mqttchat.domain.usecase.ApplicationUseCase
import com.app.mqttchat.domain.usecase.ChatUseCase
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
interface UseCaseModule {
  @Binds
  fun provideChatUseCase(useCase: ChatUseCaseImpl): ChatUseCase

  @Binds
  fun provideAppUseCase(useCase: ApplicationUseCaseImpl): ApplicationUseCase
}