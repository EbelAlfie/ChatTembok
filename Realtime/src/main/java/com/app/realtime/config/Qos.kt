package com.app.realtime.config

enum class Qos {
  AT_MOST_ONCE,
  AT_LEAST_ONCE,
  EXACTLY_ONCE;

  fun code() = this.ordinal

  companion object {

    fun fromCode(code: Int) = when (code) {
      AT_MOST_ONCE.code() -> AT_MOST_ONCE
      AT_LEAST_ONCE.code() -> AT_LEAST_ONCE
      else -> EXACTLY_ONCE
    }
  }

}