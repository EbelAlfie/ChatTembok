package com.example.chattembok.presentation.chatlist

import androidx.lifecycle.ViewModel
import com.amity.socialcloud.sdk.api.chat.channel.AmityChannelRepository
import com.amity.socialcloud.sdk.api.core.AmityCoreClient
import com.amity.socialcloud.sdk.api.core.user.AmityUserRepository
import com.example.chattembok.backend.UserDataModel
import com.example.chattembok.data.repository.chat.ChatRepository
import com.example.chattembok.presentation.chatlist.base.AmityResult
import com.example.chattembok.presentation.orderhistory.model.OrderModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

class ChatViewModel @Inject constructor(
  private val amityUserRepository: AmityUserRepository,
  private val channelRepository: AmityChannelRepository,
  private val chatRepository: ChatRepository
): ViewModel() {
  var orderData: OrderModel? = null
  var userData: UserDataModel? = null

  private val _loginState=  MutableStateFlow<AmityResult>(AmityResult.Loading)
  val loginState = _loginState.asStateFlow()

  /**
   * You must first register the current device with a userId. A device registered with a userId will be permanently tied to that userId until you deliberately unregister the device, or until the device has been inactive for more than 90 days. A device registered with a specific userId will receive all messages belonging to that user.
   **/
  fun registerToAmity() {
    userData?.let {
      AmityCoreClient.login(
        userId = it.userId,
        sessionHandler = null
      )
        .displayName(displayName = it.userName) // optional
        .authToken(authToken = "token") // optional
        .build()
        .submit()
        .doOnComplete {
          _loginState.value = AmityResult.Success
        }.doOnError {
          _loginState.value = AmityResult.Error(it)
        }
        .subscribe()
    }
  }

}