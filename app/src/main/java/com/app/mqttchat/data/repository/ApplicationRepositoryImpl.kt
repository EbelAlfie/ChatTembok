package com.app.mqttchat.data.repository

import com.app.mqttchat.data.api.RealtimeApiClient
import com.app.mqttchat.data.model.general.ApiResult
import com.app.mqttchat.domain.repository.ApplicationRepository
import com.hivemq.client.mqtt.mqtt5.message.connect.connack.Mqtt5ConnAck
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ApplicationRepositoryImpl @Inject constructor(
  private val realtimeApiClient: RealtimeApiClient
): ApplicationRepository {
  override fun establishMqttConnection(): Flow<ApiResult<Mqtt5ConnAck>> {
    return realtimeApiClient.connect()
  }
}