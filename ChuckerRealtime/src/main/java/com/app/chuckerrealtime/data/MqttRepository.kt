package com.app.chuckerrealtime.data

import com.app.chuckerrealtime.data.model.MessageEntity
import kotlinx.coroutines.flow.Flow

interface InterceptorRepository {
  fun storeMessage(newMessage: MessageEntity)

  fun getMessage(): Flow<MessageEntity>
}