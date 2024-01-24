package com.example.chattembok.presentation.orderhistory.model

import android.os.Parcelable
import com.example.chattembok.backend.monggodb.dbmodel.OrderDbModel
import kotlinx.parcelize.Parcelize

@Parcelize
data class OrderModel (
  val productName: String,
  val nomorShipment: String,
  val sellerName: String,
): Parcelable {
  companion object {
    fun transform(allOrder: List<OrderDbModel>): List<OrderModel> =
      allOrder.map {
        OrderModel(
          productName = it.productName,
          nomorShipment = it.nomorShipment,
          sellerName = it.sellerName
        )
      }
  }
}

data class ChatList (
  val roomId: String,
  val name: String
)
