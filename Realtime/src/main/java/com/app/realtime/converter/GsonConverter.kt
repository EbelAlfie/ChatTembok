package com.app.realtime.converter

import com.app.realtime.model.RealtimeMessage
import com.google.gson.Gson
import com.google.gson.TypeAdapter

class GsonConverter<type>(
  private val gson: Gson = Gson(),
  private val typeAdapter: TypeAdapter<type>
) : MessageTypeConverter<type> {

  override fun fromMessage(topic: String, message: type): RealtimeMessage {

  }

  override fun toMessage(topic: String, message: RealtimeMessage): type {
  }

}