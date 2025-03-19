package com.app.realtime.api

import java.util.UUID

data class ConnectionConfig(
  val host: String,
  val port: Int,
  val clientId: String = UUID.randomUUID().toString()
) {
  fun getServerUri() = "tcp://$host:$port"

  companion object {
    fun defaultConfig() = ConnectionConfig(
      host = "10.4.77.103",
      port = 1883
    )
  }
}
