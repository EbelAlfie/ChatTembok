package com.app.realtime.config

import java.util.UUID

data class ConnectionConfig internal constructor(
  val scheme: String,
  val host: String,
  val port: Int? = null,
  val clientId: String = UUID.randomUUID().toString(),
) {
  fun getServerUri() = "$scheme://$host" + if (port != null) ":$port" else ""

  companion object {
    fun fromScheme(scheme: String, host: String) = ConnectionConfig(
      host = host.substringBefore(":"),
      scheme = scheme,
      port = host.substringAfter(":").toInt()
    )

    fun defaultMqttConfig() = ConnectionConfig(
      host = "10.4.77.103",
      port = 8083, //8083,
      scheme = "ws" //"ws"
    )
    
    fun defaultWsConfig() = ConnectionConfig(
      host = "10.4.77.103",
      port = 8003,
      scheme = "ws"
    )
  }
}
