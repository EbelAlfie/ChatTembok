package com.app.mqttchat.presentation.chat.component

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun ChatFooter() {
  Row(
    modifier = Modifier
      .padding(43.dp)
      .fillMaxWidth()
      .padding(bottom = 36.dp)
  ) {
    MessageComposer()
  }
}

@Composable
fun MessageComposer() {
//  TextInput()
}