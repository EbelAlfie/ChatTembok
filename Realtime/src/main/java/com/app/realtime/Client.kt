package com.app.realtime

import com.app.realtime.api.RealtimeApiClient
import com.app.realtime.api.RealtimeInterceptor
import com.app.realtime.converter.MessageTypeConverter
import com.app.realtime.model.PublishRequest
import com.app.realtime.model.SubscribeRequest
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.flow.Flow

class RealtimeClient internal constructor(
  private val realtimeApiClient: RealtimeApiClient
) {
  private val scope = CoroutineScope(Dispatchers.IO + SupervisorJob())

  fun connectUser() = realtimeApiClient.connect()

  fun <msg>publishMessage(request: PublishRequest<msg>) {
    val mqttMessage = ""
    realtimeApiClient.publish(request)
  }

  fun <msg>subscribeMessage(request: SubscribeRequest): Flow<msg> {
    return realtimeApiClient.subscribe(request)
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

    fun build(client: RealtimeApiClient) = RealtimeClient(client)
  }
}