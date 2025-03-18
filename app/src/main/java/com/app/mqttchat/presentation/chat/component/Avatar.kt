package com.app.mqttchat.presentation.chat.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.app.mqttchat.R

@Composable
fun Avatar() {
  Image(
    modifier = Modifier
      .clip(CircleShape)
      .border(1.dp, Color.Gray, CircleShape),
    painter = painterResource(R.drawable.ic_chat_menu),
    contentDescription = null
  )
}