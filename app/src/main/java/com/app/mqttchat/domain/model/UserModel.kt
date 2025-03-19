package com.app.mqttchat.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class UserModel(
  val id: String,
  val username: String,
): Parcelable {

  companion object {
    const val ID = "USER_ID"
    const val NAME = "USER_NAME"
  }
}
