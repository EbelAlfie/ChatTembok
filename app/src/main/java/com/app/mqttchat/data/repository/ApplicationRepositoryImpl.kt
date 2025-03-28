package com.app.mqttchat.data.repository

import com.app.core.ApiResult
import com.app.mqttchat.domain.model.UserModel
import com.app.mqttchat.domain.repository.ApplicationRepository
import com.app.mqttchat.presentation.login.component.Network
import com.app.realtime.RealtimeClient
import com.app.realtime.config.ConnectionConfig
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ApplicationRepositoryImpl @Inject constructor(
  private val realtimeApiClient: RealtimeClient
): ApplicationRepository {
  override fun establishMqttConnection(user: UserModel): Flow<ApiResult<Boolean>> {
    val scheme = Network.getScheme(user.network)
    return realtimeApiClient.connectUser(ConnectionConfig.fromScheme(scheme, user.host))
  }
}