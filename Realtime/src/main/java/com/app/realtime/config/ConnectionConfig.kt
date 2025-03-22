package com.app.realtime.config

import com.app.realtime.interceptor.RealtimeInterceptor
import java.util.UUID

data class ConnectionConfig internal constructor(
  val host: String,
  val port: Int,
  val clientId: String = UUID.randomUUID().toString(),
  val interceptors: List<RealtimeInterceptor> = emptyList()
) {
  fun getServerUri() = "tcp://$host:$port"

  companion object {
    fun defaultConfig() = ConnectionConfig(
      host = "10.4.77.103",
      port = 1883
    )
  }
}
