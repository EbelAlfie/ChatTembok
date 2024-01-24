package com.example.chattembok.presentation.orderhistory.orderadapter

import androidx.recyclerview.widget.RecyclerView
import com.example.chatrawatinapp.databinding.ItemOrderHistoryBinding
import com.example.chattembok.presentation.orderhistory.model.OrderModel
import com.example.chattembok.presentation.orderhistory.orderadapter.OrderAdapter.ChatButtonListener

class OrderViewHolder(
  private val binding: ItemOrderHistoryBinding
): RecyclerView.ViewHolder(binding.root) {

  fun bindData(item: OrderModel, listener: ChatButtonListener?) {
    binding.apply {
      tvProduct.text = item.productName
      tvName.text = item.sellerName
      tvNoShipment.text = item.nomorShipment

      btnGotochat.setOnClickListener {
        listener?.onButtonClicked(item)
      }
    }
  }
}