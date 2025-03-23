package com.app.realtime.servicemodule

import com.app.core.ApiResult
import com.app.realtime.config.ConnectionConfig
import com.app.realtime.model.RealtimeMessage
import com.app.realtime.model.SubscribeRequest
import com.app.realtime.service.RealtimeService
import kotlinx.coroutines.flow.Flow

class WsService: RealtimeService {
  override fun connect(config: ConnectionConfig): Flow<ApiResult<Boolean>> {
    TODO("Not yet implemented")
  }

  override fun publish(message: RealtimeMessage) {
    TODO("Not yet implemented")
  }

  override fun subscribe(request: SubscribeRequest): Flow<RealtimeMessage> {
    TODO("Not yet implemented")
  }

  override fun unsubscribe() {
    TODO("Not yet implemented")
  }

  override fun disconnect() {
    TODO("Not yet implemented")
  }
}