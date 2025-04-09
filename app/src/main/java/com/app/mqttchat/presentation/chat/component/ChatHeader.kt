package com.app.mqttchat.presentation.chat.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun ChatHeader(
  onBackPressed: () -> Unit
) {
  Card(
    modifier = Modifier.fillMaxWidth(),
    colors = CardDefaults.cardColors(Color.White),
    elevation = CardDefaults.elevatedCardElevation(),
    shape = RoundedCornerShape(bottomStart = 20.dp, bottomEnd = 20.dp)
  ) {
    Row(
      modifier = Modifier
        .fillMaxWidth()
        .padding(23.dp),
      horizontalArrangement = Arrangement.spacedBy(41.dp)
    ) {
      Avatar()
      Column(
        verticalArrangement = Arrangement.spacedBy(2.dp)
      ) {
        Text(text = "Siapalah")
        Text(text = "Online")
      }
    }
  }
}