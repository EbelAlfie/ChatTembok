package com.app.realtime.model.mqtt

data class Connect(
  val username: String
)

data class ConnectAck(
  val username: String
)