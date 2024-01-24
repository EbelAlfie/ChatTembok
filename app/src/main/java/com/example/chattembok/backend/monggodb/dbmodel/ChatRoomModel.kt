package com.example.chattembok.backend.monggodb.dbmodel

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.chattembok.presentation.chatlist.model.ChatModel


@Entity(tableName = "chat_database")
data class ChatRoomModel (
  @PrimaryKey
  @ColumnInfo("channel_id")
  val channelId: String,
  @ColumnInfo("shipment_id")
  val shipmentNumber: String
) {
  companion object {
    fun transform(data: ChatRoomModel?): ChatModel {

      return ChatModel(
        data?.shipmentNumber ?: "",
        data?.channelId ?: ""
      )
    }
  }
}