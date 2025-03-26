package com.app.chuckerrealtime.di

import android.content.Context
import androidx.room.Room
import com.app.chuckerrealtime.data.source.InterceptDatabase

//@Module
//@InstallIn(SingletonComponent::class)
class RoomModule {

  //  @Provides
//  @Singleton
  fun provideDatabase(
//    @ApplicationContext context: Context
    context: Context
  ): InterceptDatabase {
    return Room.databaseBuilder(context, InterceptDatabase::class.java, "intercept-db").build()
  }

  //  @Provides
  fun provideInterceptorDao(room: InterceptDatabase) = room.interceptorService()
}