package com.app.mqttchat.presentation.chat.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.app.mqttchat.App
import com.app.mqttchat.domain.model.ChatMessageModel
import com.app.mqttchat.presentation.ui.theme.Light_Blue

@Composable
fun ChatBubble(message: ChatMessageModel) {
  val isMine = isMine(message)

  Box (modifier = Modifier.fillMaxWidth()) {
    Column(
      modifier = if (isMine) Modifier.align(Alignment.CenterEnd) else Modifier.align(Alignment.CenterStart),
    ) {
      Text(modifier = if (isMine) Modifier.align(Alignment.End) else Modifier.align(Alignment.Start), text = message.user.username)
      Card (
        modifier = Modifier
          .clip(CircleShape)
          .background(if (isMine) Light_Blue else Color.Gray, CircleShape)
          .padding(8.dp),
        shape = CircleShape,
        colors = CardDefaults.cardColors(containerColor = if (isMine) Light_Blue else Color.Gray),
        elevation = CardDefaults.elevatedCardElevation()
      ) {
        Text(text = message.text)
      }
    }
  }
}

fun isMine(message: ChatMessageModel): Boolean {
  val sender = message.user
  return sender.id == App.getUser()?.id
}