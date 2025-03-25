package com.app.realtime.config

import java.util.UUID

data class ConnectionConfig internal constructor(
  val scheme: String,
  val host: String,
  val port: Int?,
  val clientId: String = UUID.randomUUID().toString(),
) {
  fun getServerUri() = "$scheme://$host" + if (port != null) ":$port" else ""

  companion object {
    fun defaultMqttConfig() = ConnectionConfig(
      host = "10.4.77.103",
      port = 8083,
      scheme = "ws"
    )
    
    fun defaultWsConfig() = ConnectionConfig(
      host = "gtw32lpt-8003.asse.devtunnels.ms",
      port = null,
      scheme = "wss"
    )
  }
}
