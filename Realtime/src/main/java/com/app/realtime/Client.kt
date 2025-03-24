package com.app.realtime

import com.app.realtime.servicemodule.MqttService
import com.app.realtime.interceptor.RealtimeInterceptor
import com.app.realtime.config.ConnectionConfig
import com.app.realtime.converter.GsonConverter
import com.app.realtime.converter.MessageTypeConverter
import com.app.realtime.model.PublishRequest
import com.app.realtime.model.SubscribeRequest
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class RealtimeClient internal constructor(
  private val mqttServiceClient: MqttService
) {
  private val scope = CoroutineScope(Dispatchers.IO + SupervisorJob())
  private val converter: MessageTypeConverter = GsonConverter()

  fun connectUser() = mqttServiceClient.connect(ConnectionConfig.defaultConfig())

  fun <msgType> publishMessage(request: PublishRequest<msgType>, type: Class<msgType>) {
    val realtimeMessage = converter.toMessage(message = request, classType = type)
    mqttServiceClient.publish(realtimeMessage)
  }

  fun <msgType> subscribeMessage(request: SubscribeRequest, type: Class<msgType>): Flow<msgType> {
    return mqttServiceClient.subscribe(request)
      .map {
        converter.fromMessage(it, type)
          ?: type.newInstance() //TODO maybe handle error parsing event
      }
  }

  class Builder {
    private val messageTypeConverter = mutableListOf<MessageTypeConverter>()
    private val interceptors = mutableListOf<RealtimeInterceptor>()

    fun addMessageConverter(converter: MessageTypeConverter) {
      messageTypeConverter.add(converter)
    }

    fun addMqttInterceptor(interceptor: RealtimeInterceptor) {
      interceptors.add(interceptor)
    }

    fun build() = RealtimeClient(
      MqttService(interceptors)
    )
  }
}