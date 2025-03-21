package com.app.realtime.converter

import com.app.realtime.model.RealtimeMessage

interface MessageTypeConverter<type> {

  fun fromMessage(topic: String, message: type): RealtimeMessage

  fun toMessage(topic: String, message: RealtimeMessage): type
}