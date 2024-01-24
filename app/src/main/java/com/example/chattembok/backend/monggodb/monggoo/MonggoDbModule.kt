package com.example.chattembok.backend.monggodb.monggoo

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.chattembok.backend.monggodb.dbmodel.ChatRoomModel
import com.example.chattembok.backend.monggodb.dbmodel.OrderDbModel
import com.example.chattembok.backend.monggodb.operation.ChatRoomDao
import com.example.chattembok.backend.monggodb.operation.OrderDbDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@Database(
  entities = [
    OrderDbModel::class,
    ChatRoomModel::class
  ], version = 1
)
abstract class MonggoDB : RoomDatabase() {
  abstract fun orderDao(): OrderDbDao

  abstract fun chatDao(): ChatRoomDao
}

@Module
@InstallIn(SingletonComponent::class)
class MonggoDbModule {
  @Provides
  fun getDatabase(@ApplicationContext context: Context): MonggoDB {
    return Room.databaseBuilder(
      context.applicationContext,
      MonggoDB::class.java, "app_db"
    )
      .build()
  }
}

