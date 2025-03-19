package com.app.mqttchat.presentation.chatlist.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.app.mqttchat.domain.model.ChatRoomItem
import com.app.mqttchat.presentation.chat.component.Avatar

@Composable
fun ChatListItem(
  item: ChatRoomItem,
  onItemClick: (ChatRoomItem) -> Unit
) {
  Row(
    modifier = Modifier
      .fillMaxWidth()
      .clickable { onItemClick(item) }
      .padding(12.dp),
    verticalAlignment = Alignment.CenterVertically,
    horizontalArrangement = Arrangement.SpaceBetween
  ) {
    ChatInfo(item)
    Avatar()
  }
}

@Composable
fun RowScope.ChatInfo(item: ChatRoomItem) {
  Column(
    modifier = Modifier.weight(1f),
    verticalArrangement = Arrangement.spacedBy(10.dp)
  ) {
    Text(
      text = item.title,
      fontWeight = FontWeight.Bold,
      fontSize = 20.sp
    )
    Text(text = item.subtitle)
  }
}