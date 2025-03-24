package com.app.chuckerrealtime.data

import com.app.chuckerrealtime.data.model.MessageEntity
import com.app.chuckerrealtime.data.service.InterceptorService
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class InterceptorRepositoryImpl @Inject constructor(
  private val interceptDao: InterceptorService
): InterceptorRepository {
  override fun storeMessage(newMessage: MessageEntity) {
    TODO("Not yet implemented")
  }

  override fun getMessage(): Flow<MessageEntity> {
    TODO("Not yet implemented")
  }

}