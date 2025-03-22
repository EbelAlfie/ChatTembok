package com.app.chuckerrealtime.activity

import android.app.NotificationManager
import android.content.Context
import android.os.Build.VERSION
import android.os.Build.VERSION_CODES
import androidx.core.app.NotificationChannelCompat
import androidx.core.app.NotificationManagerCompat

class NotifManager(private val context: Context) {
  private val notificationService by lazy { NotificationManagerCompat.from(context) }

  init { registerChannel() }

  private fun registerChannel() {
    if (VERSION.SDK_INT >= VERSION_CODES.O) {
      val channel = NotificationChannelCompat.Builder(CHANNEL, NotificationManager.IMPORTANCE_LOW)
        .setName("Reatime chucker")
        .build()

      notificationService.createNotificationChannel(channel)
    }
  }

  fun showNotification() {

  }

  fun dismiss() {

  }

  companion object {
    const val CHANNEL = "Realtime Chucker"
  }
}