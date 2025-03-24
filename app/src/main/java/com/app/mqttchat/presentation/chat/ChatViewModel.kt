package com.app.mqttchat.presentation.chat

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.app.core.ApiResult
import com.app.mqttchat.App
import com.app.mqttchat.domain.model.ChatMessageModel
import com.app.mqttchat.domain.model.UserModel
import com.app.mqttchat.domain.usecase.ChatUseCase
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch


@HiltViewModel(assistedFactory = ChatViewModel.Companion.ChatViewModelFactory::class)
class ChatViewModel @AssistedInject constructor(
  @Assisted private val roomId: String,
  private val chatUseCase: ChatUseCase
): ViewModel() {
  private val _chatState = MutableStateFlow<List<ChatMessageModel>>(emptyList())
  val chatState: StateFlow<List<ChatMessageModel>> = _chatState

  fun loadMessages() {

  }

  fun sendMessage(message: String) {
    viewModelScope.launch {
      val user = App.getUser() ?: return@launch
      val messageRequest = ChatMessageModel(
        user = user,
        text = message
      )
      chatUseCase.sendMessage(roomId, messageRequest)
    }
  }

  fun observeMessages() {
    viewModelScope.launch {
      chatUseCase.observeMessage(roomId).collectLatest { newEvent ->
        _chatState.update {
          when (newEvent) {
            is ApiResult.Loading -> it
            is ApiResult.Error -> it + ChatMessageModel(user = UserModel(username = "System"), text = newEvent.cause.message.toString())
            is ApiResult.Success -> (it + newEvent.data).distinctBy { message -> message.id }
          }
        }
      }
    }
  }

  companion object {
    @AssistedFactory
    interface ChatViewModelFactory {
      fun create(roomId: String): ChatViewModel
    }
  }
}