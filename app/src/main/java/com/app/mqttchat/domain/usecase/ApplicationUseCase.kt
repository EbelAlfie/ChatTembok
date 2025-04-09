package com.app.mqttchat.domain.usecase

import com.app.core.ApiResult
import com.app.mqttchat.domain.model.UserModel
import com.app.mqttchat.presentation.login.component.Network
import kotlinx.coroutines.flow.Flow

interface ApplicationUseCase {
  fun establishMqttConnection(user: UserModel): Flow<ApiResult<Boolean>>
}