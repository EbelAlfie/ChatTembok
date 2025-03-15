package com.app.mqttchat.domain.usecase

import com.app.mqttchat.data.model.general.ApiResult
import com.hivemq.client.mqtt.mqtt5.message.connect.connack.Mqtt5ConnAck
import kotlinx.coroutines.flow.Flow

interface ApplicationUseCase {
  fun establishMqttConnection(): Flow<ApiResult<Mqtt5ConnAck>>
}