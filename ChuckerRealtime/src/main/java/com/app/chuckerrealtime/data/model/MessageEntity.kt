package com.app.chuckerrealtime.data.model

import androidx.room.Entity
import java.nio.ByteBuffer

@Entity
data class MessageEntity(
  val payload: ByteBuffer
)
