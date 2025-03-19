package com.app.mqttchat.data.api

interface MessageAdapter<type> {

  fun fromMessage(topic: String, message: type)

  fun toMessage(topic: String): type
}