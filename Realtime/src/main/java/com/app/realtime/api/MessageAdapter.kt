package com.app.realtime.api

interface MessageAdapter<type> {

  fun fromMessage(topic: String, message: type)

  fun toMessage(topic: String): type
}