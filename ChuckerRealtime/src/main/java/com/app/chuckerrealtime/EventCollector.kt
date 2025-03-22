package com.app.chuckerrealtime

import android.content.Context
import com.app.chuckerrealtime.activity.NotifManager
import com.app.chuckerrealtime.data.InterceptorRepository
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class EventCollector @Inject constructor(
  @ApplicationContext private val context: Context,
  private val repository: InterceptorRepository
) {

  private val notificationManager: NotifManager by lazy { NotifManager(context) }

  private fun onMessageReceived() {
    CoroutineScope(Dispatchers.IO).launch {

    }
  }
}