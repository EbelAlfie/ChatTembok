package com.example.chattembok

import android.app.Application

class ChatTembokApp: Application() {

  override fun onCreate() {
    super.onCreate()
//    AmityCoreClient.setup(
//      apiKey = BuildConfig.amityKey,
//      endpoint = AmityEndpoint.EU // optional param, defaulted as SG region
//    )
  }
}