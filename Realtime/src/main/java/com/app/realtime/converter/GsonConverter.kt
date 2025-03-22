package com.app.realtime.converter

import com.app.realtime.model.PublishRequest
import com.app.realtime.model.RealtimeMessage
import com.google.gson.Gson
import java.io.StringReader
import java.nio.ByteBuffer

class GsonConverter(
  private val gson: Gson = Gson()
) : MessageTypeConverter {

  override fun <type>fromMessage(message: RealtimeMessage, classType: Class<type>): type? {
    val reader = StringReader((message.message ?: "").toString())
    val jsonReader = gson.newJsonReader(reader)
    return try {
      gson.fromJson(jsonReader, classType)
    } catch (e: Exception) {
      e.printStackTrace()
      null
    }
  }

  override fun <type>toMessage(message: PublishRequest<type>, classType: Class<type>): RealtimeMessage {
    val payload = gson.toJson(message.message, classType)
    return RealtimeMessage(
      topic = message.topic,
      message = ByteBuffer.wrap(payload.encodeToByteArray()), //TODO should catch??
      qos = message.qos,
      retained = message.retained
    )
  }

}