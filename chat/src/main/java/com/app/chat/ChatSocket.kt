package com.app.chat

import com.app.realtime.RealtimeClient

class ChatSocket {
  private val mqttClient: RealtimeClient = RealtimeClient.Builder().buildAsMqtt()

  fun connectSocket() {
    mqttClient.connectUser()
  }
}