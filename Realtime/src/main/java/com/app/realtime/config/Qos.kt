package com.app.realtime.config

enum class Qos {
  AT_MOST_ONCE,
  AT_LEAST_ONCE,
  EXACTLY_ONCE;

  fun code() = this.ordinal
}