package com.app.realtime

import com.app.realtime.api.RealtimeApiAdapter
import com.app.realtime.api.RealtimeInterceptor
import com.app.realtime.converter.GsonConverter
import com.app.realtime.converter.MessageTypeConverter
import com.app.realtime.model.RealtimeMessage
import com.app.realtime.model.PublishRequest
import com.app.realtime.model.SubscribeRequest
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class RealtimeClient internal constructor(
  private val realtimeApiClient: RealtimeApiAdapter
) {
  private val scope = CoroutineScope(Dispatchers.IO + SupervisorJob())
  private val messageConverted: MessageTypeConverter<*> = GsonConverter<String>()

  fun connectUser() = realtimeApiClient.connect()

  fun <msg>publishMessage(request: PublishRequest<msg>) {
    val mqttMessage = messageConverted.toMessage(request.topic, request.message)
    realtimeApiClient.publish(request)
  }

  fun subscribeMessage(request: SubscribeRequest): Flow<RealtimeMessage> {
    return realtimeApiClient.subscribe(request)
      .map {

      }
  }

  class Builder {
    private val messageTypeConverter = mutableListOf<MessageTypeConverter<*>>()
    private val interceptors = mutableListOf<RealtimeInterceptor>()

    fun <type> addMessageConverter(converter: MessageTypeConverter<type>) {
      messageTypeConverter.add(converter)
    }

    fun addMqttInterceptor(interceptor: RealtimeInterceptor) {
      interceptors.add(interceptor)
    }

    fun build(client: RealtimeApiAdapter) = RealtimeClient(client)
  }
}