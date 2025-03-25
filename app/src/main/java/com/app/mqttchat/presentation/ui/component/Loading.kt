package com.app.mqttchat.presentation.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.app.mqttchat.presentation.common.UiState

@Composable
fun ConnectionLoading(uiState: UiState<*>) {
  Card(
    modifier = Modifier
      .background(Color.Gray, RoundedCornerShape(10.dp)),
    shape = RoundedCornerShape(10.dp),
    colors = CardDefaults.cardColors(contentColor = Color.Gray)
  ) {
    Column(
      modifier = Modifier.padding(10.dp),
      verticalArrangement = Arrangement.Center,
      horizontalAlignment = Alignment.CenterHorizontally
    ) {
      when (uiState) {
        is UiState.Loading -> CircularProgressIndicator()
        is UiState.Loaded -> Icon(
          imageVector = Icons.Default.Check,
          tint = Color.Green,
          contentDescription = null
        )
        is UiState.Error -> Icon(
          imageVector = Icons.Default.Close,
          tint = Color.Red,
          contentDescription = null
        )
      }
    }
  }
}