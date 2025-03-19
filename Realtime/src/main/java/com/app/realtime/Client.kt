package com.app.realtime

import com.app.realtime.api.MessageAdapter
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch

class Client internal constructor(
  private val realtimeApiClient: RealtimeApiClient
) {
  private val scope = CoroutineScope(Dispatchers.IO + SupervisorJob())

  fun connectUser() = scope.launch { realtimeApiClient.connect() }

  class Builder {
    fun <type> addMessageConverter(adapter: MessageAdapter<type>) {

    }
  }
}