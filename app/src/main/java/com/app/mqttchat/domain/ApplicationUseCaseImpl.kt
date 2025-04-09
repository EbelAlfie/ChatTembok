package com.app.mqttchat.domain

import com.app.core.ApiResult
import com.app.mqttchat.domain.model.UserModel
import com.app.mqttchat.domain.repository.ApplicationRepository
import com.app.mqttchat.domain.usecase.ApplicationUseCase
import com.app.mqttchat.presentation.login.component.Network
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ApplicationUseCaseImpl @Inject constructor(
  private val applicationRepository: ApplicationRepository
): ApplicationUseCase {

  override fun getCurrentUser(): UserModel? {
    return applicationRepository.getCurrentUser()
  }

  override fun setUser(user: UserModel) {
    applicationRepository.setUser(user)
  }


  override fun establishMqttConnection(user: UserModel): Flow<ApiResult<Boolean>> {
    return applicationRepository.establishMqttConnection(user)
  }
}