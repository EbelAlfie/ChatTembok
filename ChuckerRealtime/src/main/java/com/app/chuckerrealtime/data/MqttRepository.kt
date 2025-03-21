package com.app.chuckerrealtime.data

interface MqttRepository {
  fun storeMessage()

  fun getMessage()
}