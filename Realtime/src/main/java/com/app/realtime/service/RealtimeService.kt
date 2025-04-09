package com.app.realtime.service

import com.app.core.ApiResult
import com.app.realtime.config.ConnectionConfig
import com.app.realtime.model.RealtimeMessage
import com.app.realtime.model.SubscribeRequest
import kotlinx.coroutines.flow.Flow

interface RealtimeService {

  fun connect(config: ConnectionConfig): Flow<ApiResult<Boolean>>

  fun publish(message: RealtimeMessage)

  fun subscribe(request: SubscribeRequest): Flow<RealtimeMessage>

  fun unsubscribe()

  fun disconnect()
}