package com.app.chuckerrealtime.data

import com.app.chuckerrealtime.data.service.InterceptorService
import javax.inject.Inject

class InterceptorRepositoryImpl @Inject constructor(
  private val interceptDao: InterceptorService
): InterceptorRepository {

  override fun storeMessage() {

  }

  override fun getMessage() {

  }
}