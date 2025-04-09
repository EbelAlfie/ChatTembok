package com.app.realtime.converter

import com.app.realtime.model.PublishRequest
import com.app.realtime.model.RealtimeMessage
import com.google.gson.Gson
import com.hivemq.client.internal.util.ByteBufferUtil
import java.io.StringReader
import java.nio.ByteBuffer
import java.nio.charset.Charset

class GsonConverter(
  private val gson: Gson = Gson()
) : MessageTypeConverter {

  override val contentType: String = "application/json"

  override fun <type> toMessage(
    message: PublishRequest<type>,
    classType: Class<type>
  ): RealtimeMessage {
    val payload = gson.toJson(message.message, classType)
    return RealtimeMessage(
      topic = message.topic,
      message = ByteBuffer.wrap(payload.encodeToByteArray()), //TODO should catch??
      qos = message.qos,
      retained = message.retained
    )
  }

  override fun <type> fromMessage(message: RealtimeMessage, classType: Class<type>): type {
    val jsonString = Charset.defaultCharset().decode(message.message).toString()
    val reader = StringReader(jsonString)
    val jsonReader = gson.newJsonReader(reader)
    return gson.fromJson(jsonReader, classType)
  }

}