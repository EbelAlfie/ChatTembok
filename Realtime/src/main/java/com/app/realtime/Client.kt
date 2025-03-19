package com.app.realtime

import com.app.realtime.api.MessageAdapter

class Client internal constructor() {

  class Builder {
    fun <type>addMessageConverter(adapter: MessageAdapter<type>) {
      
    }
  }
}