package com.app.mqttchat.domain.repository

import com.app.core.ApiResult
import com.app.mqttchat.domain.model.UserModel
import com.app.mqttchat.presentation.login.component.Network
import kotlinx.coroutines.flow.Flow

interface ApplicationRepository {
  fun establishMqttConnection(user: UserModel): Flow<ApiResult<Boolean>>
  fun getCurrentUser(): UserModel?
  fun setUser(user: UserModel)
}