package com.app.realtime.converter

interface MessageTypeConverter<type> {

  fun fromMessage(topic: String, message: type): String

  fun toMessage(topic: String, message: String): type
}