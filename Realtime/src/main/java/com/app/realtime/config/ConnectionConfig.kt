package com.app.realtime.config

import java.util.UUID

data class ConnectionConfig internal constructor(
  val scheme: String,
  val host: String,
  val port: Int,
  val clientId: String = UUID.randomUUID().toString(),
) {
  fun getServerUri() = "$scheme://$host:$port"

  companion object {
    fun defaultMqttConfig() = ConnectionConfig(
      host = "10.4.77.103",
      port = 1883,
      scheme = "tcp"
    )

    fun defaultWsConfig() = ConnectionConfig(
      host = "10.4.77.103",
      port = 8003,
      scheme = "ws"
    )
  }
}
