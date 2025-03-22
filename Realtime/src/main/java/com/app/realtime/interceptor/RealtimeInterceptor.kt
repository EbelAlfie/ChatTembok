package com.app.realtime.interceptor

interface RealtimeInterceptor {
  fun onConnect()

  fun onDisconnect()

  fun onSubscribed()

  fun onPublished()

  fun onMessageReceived()
}