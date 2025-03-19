package com.app.mqttchat.presentation.navigation.argument

import android.os.Bundle
import androidx.navigation.NavType
import com.app.mqttchat.domain.model.UserModel
import com.google.gson.Gson

class UserData: NavType<UserModel>(isNullableAllowed = false) {
  override fun get(bundle: Bundle, key: String) = bundle.getParcelableArg<UserModel>(key)

  override fun parseValue(value: String): UserModel = Gson().fromJson(value, UserModel::class.java)

  override fun put(bundle: Bundle, key: String, value: UserModel) {
    bundle.putParcelable(key, value)
  }
}