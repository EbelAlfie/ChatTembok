package com.example.chattembok.presentation.login

import androidx.lifecycle.ViewModel
import com.example.chattembok.backend.UserDataModel
import com.example.chattembok.data.repository.user.UserRepository
import com.example.chattembok.presentation.login.model.LoginModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
  private val userRepository: UserRepository
): ViewModel() {
  fun validateLogin(auth: LoginModel): UserDataModel? {
    return userRepository.login(auth)
  }
}