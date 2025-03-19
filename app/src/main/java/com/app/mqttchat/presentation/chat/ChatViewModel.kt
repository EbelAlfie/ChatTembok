package com.app.mqttchat.presentation.chat

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.app.mqttchat.App
import com.app.mqttchat.domain.model.ChatMessageModel
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
  private val _chatState = MutableStateFlow<MutableList<ChatMessageModel>>(mutableListOf())
  val chatState: StateFlow<List<ChatMessageModel>> = _chatState

  fun loadMessages() {

  }

  fun sendMessage(message: String) {
    val user = App.getUser() ?: return
    val messageRequest = ChatMessageModel(
      user = user,
      text = message
    )
    chatUseCase.sendMessage(roomId, messageRequest)
  }

  fun observeMessages() {
    viewModelScope.launch {
      chatUseCase.observeMessage(roomId).collectLatest { newMessage ->
        _chatState.update { it.apply { add(newMessage) } }
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