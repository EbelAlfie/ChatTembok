package com.app.mqttchat.presentation.home

import androidx.lifecycle.ViewModel
import com.app.mqttchat.domain.usecase.ApplicationUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
  private val applicationUseCase: ApplicationUseCase
): ViewModel() {
  fun establishRealtimeConnection() {
    applicationUseCase.establishMqttConnection()
  }

}