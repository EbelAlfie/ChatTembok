package com.app.mqttchat.presentation.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.app.core.ApiResult
import com.app.mqttchat.domain.model.UserModel
import com.app.mqttchat.domain.usecase.ApplicationUseCase
import com.app.mqttchat.presentation.common.UiState
import com.app.mqttchat.presentation.login.component.Network
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
  private val applicationUseCase: ApplicationUseCase
): ViewModel() {
  private val _connectionStatus = MutableStateFlow<UiState<Boolean>>(UiState.Loading)
  val connectionStatus = _connectionStatus.asStateFlow()

  fun establishRealtimeConnection(user: UserModel) {
    viewModelScope.launch {
      applicationUseCase.establishMqttConnection(user).collectLatest { state ->
        println("VIS LOG connection result $state")
        _connectionStatus.update {
          when (state) {
            is ApiResult.Loading -> UiState.Loading
            is ApiResult.Success -> UiState.Loaded(true)
            is ApiResult.Error -> UiState.Error(state.cause)
          }
        }
      }
    }
  }

}