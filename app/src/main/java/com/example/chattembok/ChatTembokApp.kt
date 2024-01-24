package com.example.chattembok

import android.app.Application
import com.amity.socialcloud.sdk.api.core.AmityCoreClient
import com.amity.socialcloud.sdk.api.core.endpoint.AmityEndpoint
import com.example.chatrawatinapp.BuildConfig
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class ChatTembokApp: Application() {

  override fun onCreate() {
    super.onCreate()
    AmityCoreClient.setup(
      apiKey = BuildConfig.AMITY_KEY,
      endpoint = AmityEndpoint.SG
    )
  }
}