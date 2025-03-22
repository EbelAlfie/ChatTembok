package com.app.realtime.converter

import com.app.realtime.model.PublishRequest
import com.app.realtime.model.RealtimeMessage

interface MessageTypeConverter {

  fun <type>toMessage(message: PublishRequest<type>, classType: Class<type>): RealtimeMessage

  fun <type>fromMessage(message: RealtimeMessage, classType: Class<type>): type?
}