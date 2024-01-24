package com.example.chattembok.backend.monggodb.operation

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.chattembok.backend.monggodb.dbmodel.ChatRoomModel

@Dao
interface ChatRoomDao {
  @Insert(onConflict = OnConflictStrategy.REPLACE)
  fun registerChannel(model: ChatRoomModel)

  @Query("SELECT * FROM chat_database where shipment_id=:nomorShipment")
  fun getChatRoom(nomorShipment: String): ChatRoomModel?

}