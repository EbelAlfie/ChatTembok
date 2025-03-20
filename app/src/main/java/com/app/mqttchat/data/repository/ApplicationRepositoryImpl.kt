package com.app.mqttchat.data.repository

import com.app.core.ApiResult
import com.app.mqttchat.domain.repository.ApplicationRepository
import com.app.realtime.RealtimeClient
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ApplicationRepositoryImpl @Inject constructor(
  private val realtimeApiClient: RealtimeClient
): ApplicationRepository {
  override fun establishMqttConnection(): Flow<ApiResult<Boolean>> {
    return realtimeApiClient.connectUser()
  }
}