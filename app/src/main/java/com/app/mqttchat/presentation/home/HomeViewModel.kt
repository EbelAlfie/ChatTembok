package com.app.mqttchat.presentation.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.app.mqttchat.domain.usecase.ApplicationUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
  private val applicationUseCase: ApplicationUseCase
): ViewModel() {
  fun establishRealtimeConnection() {
    viewModelScope.launch {
      applicationUseCase.establishMqttConnection().collectLatest {
        println("VIS LOG connection result +$it")
      }
    }
  }

}