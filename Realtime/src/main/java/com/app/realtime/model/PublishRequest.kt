package com.app.realtime.model

data class PublishRequest(
  val topic: String,
  val message: String
)