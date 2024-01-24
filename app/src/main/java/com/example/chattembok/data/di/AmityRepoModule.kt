package com.example.chattembok.data.di

import com.amity.socialcloud.sdk.api.chat.AmityChatClient
import com.amity.socialcloud.sdk.api.chat.channel.AmityChannelRepository
import com.amity.socialcloud.sdk.api.core.AmityCoreClient
import com.amity.socialcloud.sdk.api.core.user.AmityUserRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
class AmityRepoModule {

  @Provides
  fun provideUserRepo(): AmityUserRepository {
    return AmityCoreClient.newUserRepository()
  }

  @Provides
  fun provideChanelRepo(): AmityChannelRepository {
    return AmityChatClient.newChannelRepository()
  }
}