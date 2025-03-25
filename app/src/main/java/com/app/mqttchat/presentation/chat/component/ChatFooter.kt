package com.app.mqttchat.presentation.chat.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.app.mqttchat.R
import com.app.mqttchat.presentation.ui.theme.Light_Blue

@Composable
fun ChatFooter(
  onSend: (String) -> Unit
) {
  var textMessage by remember { mutableStateOf("") }

  Row(
    modifier = Modifier
      .padding(20.dp)
      .fillMaxWidth()
      .padding(bottom = 36.dp),
    verticalAlignment = Alignment.CenterVertically,
    horizontalArrangement = Arrangement.SpaceAround
  ) {
    MessageComposer(
      textMessage = textMessage,
      onValueChanged = { textMessage = it }
    )
    SendButton(onSend = { onSend(textMessage) })
  }
}

@Composable
fun MessageComposer(
  textMessage: String,
  onValueChanged: (String) -> Unit
) {
  OutlinedTextField(
    value = textMessage,
    onValueChange = onValueChanged,
    colors = OutlinedTextFieldDefaults.colors(
      focusedBorderColor = Light_Blue,
      unfocusedBorderColor = Light_Blue
    ),
    placeholder = { Text(text = stringResource(R.string.message_composer_hint)) },
    shape = RoundedCornerShape(20.dp)
  )
}

@Composable
fun SendButton(
  onSend: () -> Unit = {}
) {
  IconButton(
    onClick = onSend,
    colors = IconButtonDefaults.iconButtonColors(containerColor = Light_Blue),
    content = {
      Icon(
        modifier = Modifier.padding(10.dp),
        painter = painterResource(R.drawable.ic_send),
        tint = Color.White,
        contentDescription = null
      )
    }
  )
}