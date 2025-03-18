package com.app.mqttchat.presentation.login.component

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.app.mqttchat.R

@Composable
fun AppTitle() {
  Text(
    text = stringResource(R.string.app_name),
    color = Color.Black,
    fontSize = 50.sp,
    fontWeight = FontWeight.Bold
  )
}