package com.app.mqttchat.presentation.chat.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun ChatHeader() {
  Row(
    modifier = Modifier.padding(23.dp),
    horizontalArrangement = Arrangement.spacedBy(41.dp)
  ) {
    Avatar()
    Column(
      verticalArrangement = Arrangement.spacedBy(2.dp)
    ) {
      Text(text = "Liz")
      Text(text = "is Typing")
    }
  }
}