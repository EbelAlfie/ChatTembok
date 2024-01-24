package com.example.chattembok.presentation.orderhistory.orderadapter

import androidx.recyclerview.widget.DiffUtil
import com.example.chattembok.presentation.orderhistory.model.OrderModel

object OrderDiffUtil: DiffUtil.ItemCallback<OrderModel>() {
  override fun areItemsTheSame(oldItem: OrderModel, newItem: OrderModel)
  = oldItem.hashCode() == newItem.hashCode()

  override fun areContentsTheSame(oldItem: OrderModel, newItem: OrderModel)
  = oldItem == newItem
}