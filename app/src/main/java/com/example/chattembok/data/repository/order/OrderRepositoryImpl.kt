package com.example.chattembok.data.repository.order

import com.example.chattembok.backend.monggodb.dbmodel.OrderDbModel
import com.example.chattembok.backend.monggodb.monggoo.MonggoDB
import com.example.chattembok.backend.monggodb.operation.OrderDbDao
import com.example.chattembok.backend.orderrouter.Order
import com.example.chattembok.presentation.orderhistory.model.OrderModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class OrderRepositoryImpl @Inject constructor(
  private val orderRoute: OrderDbDao
): OrderRepository {
  override suspend fun getOrderData(userId: String): Flow<List<OrderModel>> {
    return flow {
      val allOrder = orderRoute.getAllOrder(userId)

      emit(OrderModel.transform(allOrder))
    }
  }

  override suspend fun addOrderData(userId: String): Long {
    val order = Order.generateRandomOrder()
    val orderDb = OrderDbModel.transform(order, userId)
    return orderRoute.insertOrderHistory(orderDb)
  }
}