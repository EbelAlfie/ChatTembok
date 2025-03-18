package com.app.mqttchat.presentation.chat

import androidx.lifecycle.ViewModel
import com.app.mqttchat.domain.usecase.ChatUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class ChatViewModel @Inject constructor(
  private val chatUseCase: ChatUseCase
): ViewModel() {
  private val _chatState = MutableStateFlow(listOf(""))
  val chatState = _chatState.asStateFlow()

  fun loadMessages() {

  }

  fun sendMessage() {

  }

}