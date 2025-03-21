package com.app.chuckerrealtime.activity

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.app.chuckerrealtime.data.InterceptorRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class InterceptorViewModel @Inject constructor(
  private val repository: InterceptorRepository
): ViewModel() {
  private val _interceptedMessages = MutableStateFlow<List<String>>(emptyList())
  val interceptedMessages = _interceptedMessages.asStateFlow()

  fun listenInterceptedMessages() {
    viewModelScope.launch {

    }
  }
}