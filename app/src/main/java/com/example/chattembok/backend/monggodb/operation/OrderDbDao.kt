package com.example.chattembok.backend.monggodb.operation

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.chattembok.backend.monggodb.dbmodel.OrderDbModel

@Dao
interface OrderDbDao {

  @Insert(onConflict = OnConflictStrategy.REPLACE)
  fun insertOrderHistory(orderDbModel: OrderDbModel): Long

  @Query("SELECT * FROM order_database WHERE cust_id=:userId")
  fun getAllOrder(userId: String): List<OrderDbModel>
}