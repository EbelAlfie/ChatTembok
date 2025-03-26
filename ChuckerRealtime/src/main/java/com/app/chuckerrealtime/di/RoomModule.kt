package com.app.chuckerrealtime.di

import android.content.Context
import androidx.room.Room
import com.app.chuckerrealtime.data.source.InterceptDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RoomModule {

  @Provides
  @Singleton
  fun provideDatabase(
    @ApplicationContext context: Context
  ): InterceptDatabase {
    return Room.databaseBuilder(context, InterceptDatabase::class.java, "intercept-db").build()
  }

  @Provides
  fun provideInterceptorDao(room: InterceptDatabase) = room.interceptorService()
}