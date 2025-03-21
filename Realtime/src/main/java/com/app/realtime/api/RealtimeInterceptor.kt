package com.app.realtime.api

interface RealtimeInterceptor {
  fun onConnect()

  fun onDisconnect()

  fun onSubscribed()

  fun onPublished()

  fun onMessageReceived()
}