package com.app.mqttchat.data.repository

import com.app.core.ApiResult
import com.app.mqttchat.domain.repository.ApplicationRepository
import com.app.realtime.RealtimeApiClient
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ApplicationRepositoryImpl @Inject constructor(
  private val realtimeApiClient: RealtimeApiClient
): ApplicationRepository {
  override fun establishMqttConnection(): Flow<ApiResult<Boolean>> {
    return realtimeApiClient.connect()
  }
}