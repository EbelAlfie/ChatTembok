package com.example.chattembok.presentation.orderhistory.orderadapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.example.chatrawatinapp.databinding.ItemOrderHistoryBinding
import com.example.chattembok.presentation.orderhistory.model.OrderModel

class OrderAdapter : ListAdapter<OrderModel, OrderViewHolder>(OrderDiffUtil) {

  private var mListener: ChatButtonListener? = null

  fun setButtonListener (listener: ChatButtonListener) {
    mListener = listener
  }

  interface ChatButtonListener {
    fun onButtonClicked(item: OrderModel)
  }
  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = OrderViewHolder(
    ItemOrderHistoryBinding.inflate(LayoutInflater.from(parent.context), parent, false)
  )

  override fun onBindViewHolder(holder: OrderViewHolder, position: Int) {
    val item = currentList[position]
    holder.bindData(item, mListener)
  }

  fun addItem(item: OrderModel) {
    currentList.add(item)
  }
}