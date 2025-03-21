package com.app.chuckerrealtime.data.source

import androidx.room.Database
import androidx.room.RoomDatabase
import com.app.chuckerrealtime.data.model.MessageEntity
import com.app.chuckerrealtime.data.service.InterceptorService

@Database(entities = [MessageEntity::class], version = 1)
abstract class InterceptDatabase: RoomDatabase() {
  abstract fun interceptorService(): InterceptorService
}