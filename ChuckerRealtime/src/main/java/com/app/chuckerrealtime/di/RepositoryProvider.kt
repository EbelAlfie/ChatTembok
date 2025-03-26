package com.app.chuckerrealtime.di

import com.app.chuckerrealtime.data.InterceptorRepository
import com.app.chuckerrealtime.data.InterceptorRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface RepositoryProvider {
  @Binds
  fun provideInterceptorRepository(repository: InterceptorRepositoryImpl): InterceptorRepository
}