package com.app.realtime.api

data class RealtimeEvent<eventModel>(
  val type: String,
  val data: eventModel
)
