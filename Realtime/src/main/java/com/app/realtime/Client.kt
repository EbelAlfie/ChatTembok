package com.app.realtime

import com.app.realtime.config.ConnectionConfig
import com.app.realtime.converter.GsonConverter
import com.app.realtime.converter.MessageTypeConverter
import com.app.realtime.interceptor.RealtimeInterceptor
import com.app.realtime.model.PublishRequest
import com.app.realtime.model.SubscribeRequest
import com.app.realtime.service.RealtimeService
import com.app.realtime.servicemodule.MqttService
import com.app.realtime.servicemodule.WsService
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class RealtimeClient internal constructor(
  private val connectionConfig: ConnectionConfig,
  private val service: RealtimeService
) {
  private val converter: MessageTypeConverter = GsonConverter()

  fun connectUser(config: ConnectionConfig?) = service.connect(config ?: connectionConfig)

  fun <msgType> publishMessage(request: PublishRequest<msgType>, type: Class<msgType>) {
    val realtimeMessage = converter.toMessage(message = request, classType = type)
    service.publish(realtimeMessage)
  }

  fun <msgType> subscribeMessage(request: SubscribeRequest, type: Class<msgType>): Flow<msgType> {
    return service.subscribe(request)
      .map { converter.fromMessage(it, type) }
  }

  class Builder {
    private val messageTypeConverter = mutableListOf<MessageTypeConverter>()
    private val interceptors = mutableListOf<RealtimeInterceptor>()

    private var connectionConfig = ConnectionConfig.defaultWsConfig()

    fun addMessageConverter(converter: MessageTypeConverter) {
      messageTypeConverter.add(converter)
    }

    fun addMqttInterceptor(interceptor: RealtimeInterceptor) {
      interceptors.add(interceptor)
    }

    fun setConnectionConfig(newConfig: ConnectionConfig) {
      connectionConfig = newConfig
    }

    fun build() = buildAsMqtt()

    private fun buildAsWs() = RealtimeClient(connectionConfig, WsService())
    private fun buildAsMqtt() = RealtimeClient(connectionConfig, MqttService(interceptors))
  }
}