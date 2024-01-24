package com.example.chattembok.data.repository.order

import com.example.chattembok.presentation.orderhistory.model.OrderModel
import kotlinx.coroutines.flow.Flow

interface OrderRepository {
  suspend fun getOrderData(userId: String): Flow<List<OrderModel>>
  suspend fun addOrderData(s: String): Long
}