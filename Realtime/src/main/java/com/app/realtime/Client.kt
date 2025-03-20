package com.app.realtime

import com.app.realtime.api.RealtimeApiClient
import com.app.realtime.converter.MessageTypeConverter
import com.app.realtime.model.PublishRequest
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch

class RealtimeClient internal constructor(
  private val realtimeApiClient: RealtimeApiClient
) {
  private val scope = CoroutineScope(Dispatchers.IO + SupervisorJob())

  fun connectUser() = scope.launch { realtimeApiClient.connect() }

  fun <msg>publishMessage(request: PublishRequest) {

  }

  fun <msg>subscribeMessage() {

  }

  class Builder {
    private val messageTypeConverter = mutableListOf<MessageTypeConverter<*>>()

    fun <type> addMessageConverter(converter: MessageTypeConverter<type>) {
      messageTypeConverter.add(converter)
    }

    fun build(client: RealtimeApiClient) = RealtimeClient(client)
  }
}