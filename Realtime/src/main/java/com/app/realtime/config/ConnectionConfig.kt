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
      host = "192.168.231.13",
      port = 8083, //8083,
      scheme = "ws" //"ws"
    )
    
    fun defaultWsConfig() = ConnectionConfig(
      host = "192.168.231.13",
      port = 8003,
      scheme = "ws"
    )
  }
}
