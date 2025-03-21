package com.app.chuckerrealtime.data.source

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class RoomModule {

  @Provides
  fun provideDatabase(
    @ApplicationContext context: Context
  ): RoomDatabase {
    return Room.databaseBuilder(context, InterceptDatabase::class.java, "intercept-db").build()
  }

}