package com.example.chattembok.presentation.orderhistory

import android.content.Context
import android.content.Intent
import android.os.Build.VERSION
import android.os.Build.VERSION_CODES
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.amity.socialcloud.sdk.model.chat.channel.AmityChannel
import com.amity.socialcloud.uikit.chat.messages.AmityMessageListActivity
import com.example.chatrawatinapp.databinding.ActivityOrderHistoryBinding
import com.example.chattembok.backend.UserDataModel
import com.example.chattembok.presentation.chatlist.base.StatusHandler
import com.example.chattembok.presentation.orderhistory.model.OrderModel
import com.example.chattembok.presentation.orderhistory.orderadapter.OrderAdapter
import com.example.chattembok.presentation.orderhistory.orderadapter.OrderAdapter.ChatButtonListener
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class OrderHistoryActivity: AppCompatActivity() {

  private val viewModel: OrderViewModel by viewModels()

  private val orderAdapter: OrderAdapter by lazy {
    OrderAdapter()
  }

  private val binding: ActivityOrderHistoryBinding by lazy {
    ActivityOrderHistoryBinding.inflate(layoutInflater)
  }
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(binding.root)
    loadData()
    setupRecyclerView()
    setupObservers()
    setAddButton()
    viewModel.getOrderHistory()
  }

  private fun setupObservers() {
    viewModel.orderModel.observe(this) {
      orderAdapter.submitList(it)
    }

    viewModel.chatRoom.observe(this) {
      if (it.channelId.isBlank())
        viewModel.createChatRoom()
      else viewModel.getChatRoom(it.channelId)
    }

    viewModel.channelStatus.observe(this) {
      when (it) {
        is StatusHandler.Success -> {
          gotoChatRoomActivity(it.data)
        }
        is StatusHandler.Error -> {
          Toast.makeText(this, "Error", Toast.LENGTH_SHORT).show()
        }
        else -> {}
       }
    }
  }

  private fun loadData() {
    viewModel.userData =  if (VERSION.SDK_INT >= VERSION_CODES.TIRAMISU) {
      intent.getParcelableExtra(EXTRA_USER, UserDataModel::class.java)
    } else intent.getParcelableExtra(EXTRA_USER)
  }

  private fun setAddButton() {
    binding.btnOrder.setOnClickListener {
      addOrderData()
    }
  }

  private fun setupRecyclerView() {
    orderAdapter.setButtonListener(object: ChatButtonListener {
      override fun onButtonClicked(item: OrderModel) {
        viewModel.selectedOrder = item
        getChatData(item.nomorShipment)
      }

    })
    binding.rvOrderHistory.apply {
      layoutManager = LinearLayoutManager(this@OrderHistoryActivity)
      adapter = orderAdapter
    }
  }

  private fun addOrderData() {
    viewModel.addOrderHistory()
  }

  private fun gotoChatRoomActivity(item: AmityChannel) {
    viewModel.userData?.let {
      val messageIntent = AmityMessageListActivity
        .newIntent(this, item.getChannelId())
      startActivity(messageIntent)
    }
  }

  private fun getChatData(nomorShipment: String) {
    viewModel.getChannelByShipmentId(nomorShipment)
  }

  companion object {
    const val EXTRA_USER = "EXTRA_USER"
    fun newIntent(from: Context, loginData: UserDataModel): Intent {
      return Intent(from, OrderHistoryActivity::class.java).also {
        it.putExtra(EXTRA_USER, loginData)
      }
    }
  }
}