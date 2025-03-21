package com.app.realtime.converter

import com.app.realtime.model.MqttMessage

interface MessageTypeConverter<type> {

  fun fromMessage(topic: String, message: type): MqttMessage

  fun toMessage(topic: String, message: MqttMessage): type
}