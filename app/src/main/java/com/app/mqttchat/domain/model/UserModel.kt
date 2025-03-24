package com.app.mqttchat.domain.model

import android.os.Parcelable
import com.app.mqttchat.data.model.UserResponse
import kotlinx.parcelize.Parcelize

@Parcelize
data class UserModel(
  val id: String,
  val username: String,
): Parcelable {

  companion object {
    const val ID = "USER_ID"
    const val NAME = "USER_NAME"

    fun toUserResponse(user: UserModel) = UserResponse(
      id = user.id,
      username = user.username
    )
  }
}
