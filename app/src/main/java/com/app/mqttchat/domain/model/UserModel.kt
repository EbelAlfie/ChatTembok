package com.app.mqttchat.domain.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class UserModel(
  val id: String,
  val username: String,
): Parcelable
