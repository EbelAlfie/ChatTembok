package com.example.chattembok.data.repository.user

import com.example.chattembok.backend.UserDataModel
import com.example.chattembok.presentation.login.model.LoginModel
import kotlinx.coroutines.flow.Flow

interface UserRepository {
  fun login(auth: LoginModel): UserDataModel?
}