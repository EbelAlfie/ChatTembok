package com.app.mqttchat.domain

import com.app.core.ApiResult
import com.app.mqttchat.domain.repository.ApplicationRepository
import com.app.mqttchat.domain.usecase.ApplicationUseCase
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ApplicationUseCaseImpl @Inject constructor(
  private val applicationRepository: ApplicationRepository
): ApplicationUseCase {
  override fun establishMqttConnection(): Flow<ApiResult<Boolean>> {
    return applicationRepository.establishMqttConnection()
  }
}