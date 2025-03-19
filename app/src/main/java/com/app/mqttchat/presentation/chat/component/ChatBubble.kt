package com.app.mqttchat.presentation.chat.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.lazy.LazyItemScope
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import com.app.mqttchat.domain.model.MessageModel

@Composable
fun ChatBubble(message: MessageModel) {
  Column {
    Text(text = message.user.username)
    Card (
      modifier = Modifier
        .clip(CircleShape)
        .background(Color.Blue, CircleShape),
      shape = CircleShape,
      elevation = CardDefaults.elevatedCardElevation()
    ) {
      Text(text = message.text)
    }
  }
}