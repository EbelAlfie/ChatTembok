package com.app.chuckerrealtime

import android.content.Context
import com.app.chuckerrealtime.activity.NotifManager
import com.app.chuckerrealtime.data.InterceptorRepository
import com.app.chuckerrealtime.data.model.MessageEntity
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.collectLatest
import javax.inject.Inject

class EventCollector @Inject constructor(
  @ApplicationContext private val context: Context,
  private val repository: InterceptorRepository
) {

  private val notificationManager: NotifManager by lazy { NotifManager(context) }

  fun onNewMessage(newMessage: MessageEntity) {
    repository.storeMessage(newMessage)
  }

  suspend fun observeNewMessage() {
    repository.getMessage().collectLatest {

    }
  }
}