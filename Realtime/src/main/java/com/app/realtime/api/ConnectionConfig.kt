package com.app.mqttchat.data.api

import java.util.UUID

data class ConnectionConfig(
  val host: String,
  val port: Int,
  val clientId: String = UUID.randomUUID().toString()
) {
  fun getServerUri() = "tcp://$host:$port"
}
