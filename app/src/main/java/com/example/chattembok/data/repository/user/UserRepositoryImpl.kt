package com.example.chattembok.data.repository.user

import com.example.chattembok.backend.DUMMYDATA
import com.example.chattembok.backend.UserDataModel
import com.example.chattembok.presentation.login.model.LoginModel
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(): UserRepository {
  override fun login(auth: LoginModel): UserDataModel?  {
    val users = DUMMYDATA.custList.find {
      auth.name == it.userName && auth.pass == it.userId
    }
    return users
  }
}