package com.app.mqttchat.domain.usecase

import com.app.core.ApiResult
import kotlinx.coroutines.flow.Flow

interface ApplicationUseCase {
  fun establishMqttConnection(): Flow<ApiResult<Boolean>>
}