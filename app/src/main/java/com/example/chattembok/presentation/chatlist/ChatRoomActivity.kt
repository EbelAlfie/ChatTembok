package com.example.chattembok.presentation.chatlist

import android.content.Context
import android.content.Intent
import android.content.Intent.EXTRA_USER
import android.os.Build.VERSION
import android.os.Build.VERSION_CODES
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.example.chattembok.backend.UserDataModel
import com.example.chattembok.presentation.chatlist.base.AmityResult
import com.example.chattembok.presentation.orderhistory.OrderHistoryActivity
import com.example.chattembok.presentation.orderhistory.OrderHistoryActivity.Companion
import com.example.chattembok.presentation.orderhistory.model.OrderModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class ChatRoomActivity: AppCompatActivity() {

  private val viewModel: ChatViewModel by viewModels()
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    loadLoginData()
    registerObservers()
    registerToAmity()
  }

  private fun registerObservers() {
    lifecycleScope.launch {
      viewModel.loginState.collectLatest {
        when (it) {
          is AmityResult.Loading -> {}
          is AmityResult.Success -> {}
          is AmityResult.Error -> {
            println("AMITYYYY ERRORR ${it.throwable.message}")
          }
        }
      }
    }
  }

  private fun registerToAmity() {
    viewModel.registerToAmity()
  }

  private fun loadLoginData() {
    viewModel.userData = if (VERSION.SDK_INT >= VERSION_CODES.TIRAMISU) {
      intent.getParcelableExtra(OrderHistoryActivity.EXTRA_USER, UserDataModel::class.java)
    } else intent.getParcelableExtra(OrderHistoryActivity.EXTRA_USER)

    viewModel.orderData = if (VERSION.SDK_INT >= VERSION_CODES.TIRAMISU) {
      intent.getParcelableExtra(EXTRA_ORDER_ITEM, OrderModel::class.java)
    } else intent.getParcelableExtra(EXTRA_ORDER_ITEM)
  }

  companion object {
    const val EXTRA_ORDER_ITEM = "EXTRAJOSSSS"
    fun newIntent(from: Context, item: OrderModel, data: UserDataModel): Intent {
      return Intent(from, ChatRoomActivity::class.java).also {
        it.putExtra(EXTRA_ORDER_ITEM, item)
        it.putExtra(EXTRA_USER, data)
      }
    }
  }
}