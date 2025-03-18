package com.app.mqttchat.presentation.navigation.argument

import android.net.Uri
import android.os.Bundle
import android.os.Parcelable
import androidx.core.os.BundleCompat
import com.google.gson.Gson

fun Parcelable.encodeToJson(): String =
  Uri.encode(Gson().toJson(this, this::class.java))

inline fun <reified model: Parcelable> Bundle.getParcelableArg(key: String): model? =
  BundleCompat.getParcelable(this, key, model::class.java)

fun String.putArgument(key: String, value: String) =
  replace("{$key}", value)

fun String.putParcelable(key: String, value: Parcelable) =
  putArgument(key, value.encodeToJson())