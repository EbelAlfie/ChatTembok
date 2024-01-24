package com.example.chattembok.presentation.orderhistory

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.amity.socialcloud.sdk.api.chat.channel.AmityChannelRepository
import com.amity.socialcloud.sdk.model.chat.channel.AmityChannel
import com.amity.socialcloud.sdk.model.core.tag.AmityTags
import com.example.chattembok.backend.UserDataModel
import com.example.chattembok.data.repository.chat.ChatRepository
import com.example.chattembok.data.repository.order.OrderRepository
import com.example.chattembok.presentation.chatlist.base.StatusHandler
import com.example.chattembok.presentation.chatlist.model.ChatModel
import com.example.chattembok.presentation.orderhistory.model.OrderModel
import com.google.gson.JsonObject
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class OrderViewModel @Inject constructor(
  private val orderRepository: OrderRepository,
  private val chatRepository: ChatRepository,
  private val channelRepository: AmityChannelRepository
) : ViewModel() {
  var userData: UserDataModel? = null

  var selectedOrder: OrderModel? = null

  var orderModel = MutableLiveData<List<OrderModel>>(listOf())

  var chatRoom = MutableLiveData<ChatModel>()

  val channelStatus = MutableLiveData<StatusHandler<AmityChannel>>()

  fun getOrderHistory() {
    CoroutineScope(Dispatchers.IO).launch {
      orderRepository.getOrderData(userData?.userId ?: "").collectLatest {
        try {
          orderModel.postValue(it)
        } catch (e: Throwable) {
          Log.d("DBB", e.message.toString())
        }
      }
    }
  }

  fun addOrderHistory() {
    CoroutineScope(Dispatchers.IO).launch {
      try {
        orderRepository.addOrderData(userData?.userId ?: "")
      } catch (e: Throwable) {
        Log.d("DBB", e.message.toString())
      } finally {
        getOrderHistory()
      }
    }
  }

  fun getChannelByShipmentId(nomorShipment: String) {
    CoroutineScope(Dispatchers.IO).launch {
      chatRoom.postValue(chatRepository.getChatRoom(nomorShipment))
    }
  }

  fun createChatRoom() {
    createChannel()
  }

  private fun createChannel() {
    selectedOrder?.let {
      channelRepository.createChannel(displayName = it.sellerName)
        .community()
        .metadata(metadata = JsonObject()) // optional
        .tags(tags = AmityTags(listOf("Promotion", "New Arrival"))) // optional
        .isPublic(false)
        .build()
        .create()
        .doOnSuccess { channel: AmityChannel ->
          channelStatus.postValue (
            StatusHandler.Success(channel)
          )
        }
        .doOnError {
          channelStatus.postValue (
            StatusHandler.Error(it)
          )
        }
        .subscribe()
    }

  }

  fun getChatRoom(channelId: String) {
    channelRepository.getChannel(channelId)
      .doOnNext { channel: AmityChannel ->
        channelStatus.postValue (
          StatusHandler.Success(channel)
        )
      }
      .doOnError {
        channelStatus.postValue (
          StatusHandler.Error(it)
        )
      }
      .subscribe()
  }
}