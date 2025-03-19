package com.app.mqttchat.presentation.ui.component

import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

object GeneralButtonColors {

}

@Composable
fun GeneralButton(
  modifier: Modifier = Modifier,
  onClick: () -> Unit = {},
  content: @Composable RowScope.() -> Unit
) {
  Button(
    modifier = modifier,
    onClick = onClick,
    shape = RoundedCornerShape(10.dp),
    colors = ButtonDefaults.buttonColors(),
    content = content
  )
}