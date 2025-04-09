package com.app.mqttchat.data.repository

import com.app.core.ApiResult
import com.app.mqttchat.data.source.LocalSource
import com.app.mqttchat.domain.model.UserModel
import com.app.mqttchat.domain.repository.ApplicationRepository
import com.app.mqttchat.presentation.login.component.Network
import com.app.realtime.RealtimeClient
import com.app.realtime.config.ConnectionConfig
import com.app.realtime.model.SubscribeRequest
import com.app.realtime.model.SubscribeRequest.Companion
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ApplicationRepositoryImpl @Inject constructor(
  private val localSource: LocalSource,
  private val realtimeApiClient: RealtimeClient
): ApplicationRepository {

  override fun getCurrentUser(): UserModel? {
    return localSource.getCurrentUser()
  }

  override fun setUser(user: UserModel) {
    localSource.setUser(user)
  }

  override fun establishMqttConnection(user: UserModel): Flow<ApiResult<Boolean>> {
    val scheme = Network.getScheme(user.network)
    return realtimeApiClient.connectUser(ConnectionConfig.fromScheme(scheme, user.host))
  }
}