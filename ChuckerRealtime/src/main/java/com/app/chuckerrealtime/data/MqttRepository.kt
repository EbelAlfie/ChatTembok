package com.app.chuckerrealtime.data

interface InterceptorRepository {
  fun storeMessage()

  fun getMessage()
}