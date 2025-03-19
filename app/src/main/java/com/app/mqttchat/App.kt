package com.app.mqttchat

import android.app.Application
import android.content.Context
import com.app.mqttchat.domain.model.UserModel
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class App : Application() {

  override fun onCreate() {
    super.onCreate()
    app = this
  }

  companion object {
    const val SHARED_PREFERENCE = "user_preference"

    lateinit var app: App

    fun setUser(user: UserModel) {
      app.getSharedPreferences(SHARED_PREFERENCE, Context.MODE_PRIVATE)
        .edit()
        .putString(UserModel.ID, user.id)
        .putString(UserModel.NAME, user.username)
        .apply()
    }

    fun getUser(): UserModel? {
      val preference = app.getSharedPreferences(SHARED_PREFERENCE, Context.MODE_PRIVATE)
      val id = preference.getString(UserModel.ID, "") ?: return null
      val name = preference.getString(UserModel.NAME, "") ?: return null
      return UserModel(
        id = id,
        username = name
      )
    }
  }
}