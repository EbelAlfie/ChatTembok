package com.example.chattembok.backend.monggodb.dbmodel

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.chattembok.presentation.orderhistory.model.OrderModel

@Entity(tableName = "order_database")
data class OrderDbModel (
  @PrimaryKey
  @ColumnInfo("shipment_id")
  val nomorShipment: String,
  @ColumnInfo("cust_id")
  val userId: String,
  @ColumnInfo("product")
  val productName: String,
  @ColumnInfo("seller")
  val sellerName: String,
) {
  companion object {

    fun transform(item: OrderModel, userId: String): OrderDbModel {
      return OrderDbModel(
        nomorShipment = item.nomorShipment,
        productName = item.productName,
        sellerName = item.sellerName,
        userId = userId
      )
    }

  }
}