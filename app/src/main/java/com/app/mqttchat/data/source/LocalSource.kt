package com.app.mqttchat.data.source

import android.content.Context
import com.app.mqttchat.domain.model.UserModel
import com.app.mqttchat.presentation.login.component.Network
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class LocalSource @Inject constructor(
  @ApplicationContext private val context: Context
) {

  private val SHARED_PREFERENCE = "user_preference"

  fun getCurrentUser(): UserModel? {
    val preference = context.getSharedPreferences(SHARED_PREFERENCE, Context.MODE_PRIVATE)
    val id = preference.getString(UserModel.ID, "") ?: return null
    val name = preference.getString(UserModel.NAME, "") ?: return null
    val host = preference.getString(UserModel.HOST, "") ?: return null
    val network = preference.getString(UserModel.NETWORK, "") ?: return null
    return UserModel(
      id = id,
      username = name,
      host = host,
      network = Network.valueOf(network)
    )
  }

  fun setUser(user: UserModel) {
    context.getSharedPreferences(SHARED_PREFERENCE, Context.MODE_PRIVATE)
      .edit()
      .putString(UserModel.ID, user.id)
      .putString(UserModel.NAME, user.username)
      .putString(UserModel.HOST, user.host)
      .putString(UserModel.NETWORK, user.network.name)
      .apply()
  }
}