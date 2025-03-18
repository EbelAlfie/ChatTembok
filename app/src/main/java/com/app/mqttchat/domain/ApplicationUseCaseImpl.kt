package com.app.mqttchat.domain

import com.app.mqttchat.data.model.general.ApiResult
import com.app.mqttchat.domain.repository.ApplicationRepository
import com.app.mqttchat.domain.usecase.ApplicationUseCase
import com.hivemq.client.mqtt.mqtt5.message.connect.connack.Mqtt5ConnAck
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ApplicationUseCaseImpl @Inject constructor(
  private val applicationRepository: ApplicationRepository
): ApplicationUseCase {
  override fun establishMqttConnection(): Flow<ApiResult<Mqtt5ConnAck>> {
    return applicationRepository.establishMqttConnection()
  }
}