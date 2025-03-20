package com.app.mqttchat.data.model

import com.app.mqttchat.domain.model.UserModel
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class UserResponse(
  @Expose
  @SerializedName("id")
  val id: String,
  @Expose
  @SerializedName("username")
  val username: String,
) {
  companion object {

    fun transform(response: UserResponse): UserModel {
      return UserModel(
        id = response.id,
        username = response.username
      )
    }

  }
}
