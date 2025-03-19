package com.app.mqttchat.domain.repository

import com.app.core.ApiResult
import kotlinx.coroutines.flow.Flow

interface ApplicationRepository {
  fun establishMqttConnection(): Flow<ApiResult<Boolean>>
}