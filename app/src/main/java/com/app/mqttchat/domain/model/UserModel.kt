package com.app.mqttchat.domain.model

import android.os.Parcelable
import com.app.mqttchat.data.model.UserResponse
import com.app.mqttchat.presentation.login.component.Network
import kotlinx.parcelize.Parcelize
import java.util.UUID

@Parcelize
data class UserModel(
  val id: String = UUID.randomUUID().toString(),
  val username: String,
  val host: String = "",
  val network: Network = Network.MQTT,
): Parcelable {

  companion object {
    const val ID = "USER_ID"
    const val NAME = "USER_NAME"
    const val HOST = "HOST"
    const val NETWORK = "NETWORK"

    fun toUserResponse(user: UserModel) = UserResponse(
      id = user.id,
      username = user.username
    )
  }
}
