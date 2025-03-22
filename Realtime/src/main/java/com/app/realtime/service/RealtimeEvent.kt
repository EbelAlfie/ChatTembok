package com.app.realtime.service

data class RealtimeEvent<eventModel>(
  val type: String,
  val data: eventModel
)
