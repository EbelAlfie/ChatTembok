package com.app.realtime.converter

import com.app.realtime.model.MqttMessage
import com.google.gson.Gson
import com.google.gson.TypeAdapter
import com.google.gson.reflect.TypeToken

class GsonConverter<type>(
  private val gson: Gson
) : MessageTypeConverter<type> {

  override fun fromMessage(topic: String, message: type): MqttMessage {
    val adapter: TypeAdapter<type> = gson.getAdapter(TypeToken.get(type))
  }

  override fun toMessage(topic: String, message: MqttMessage): type {
    val adapter: TypeAdapter<type> = gson.getAdapter(TypeToken.get(type))
  }

}