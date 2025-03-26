package com.app.realtime

object Utils {
  fun isWebSocketScheme(scheme: String) = scheme == "ws" || scheme == "wss"
}