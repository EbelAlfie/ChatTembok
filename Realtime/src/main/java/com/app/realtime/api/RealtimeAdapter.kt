package com.app.realtime.api

import com.app.realtime.model.RealtimeMessage
import kotlinx.coroutines.flow.Flow

interface RealtimeAdapter {

  fun connect()

  fun publish(message: RealtimeMessage)

  fun subscribe(): Flow<RealtimeMessage>

  fun unsubscribe()

  fun disconnect()
}