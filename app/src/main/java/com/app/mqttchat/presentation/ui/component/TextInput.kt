package com.app.mqttchat.presentation.ui.component

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp

val ChatTextStyle = TextStyle.Default.copy(
  color = Color.White
)

object CustomTextFieldDefault {
  val shape: Shape = RoundedCornerShape(20.dp)
  val colors: TextFieldColors
    @Composable get() = OutlinedTextFieldDefaults.colors(
      unfocusedContainerColor = Color.Gray,
      unfocusedPlaceholderColor = Color.Gray,
    )
}

@Composable
fun TextInput(
  value: String,
  onValueChange: (String) -> Unit,
  placeholder: @Composable () -> Unit = {
    Text(text = "Your name")
  },
  ) {
  OutlinedTextField(
    value = value,
    onValueChange = onValueChange,
    placeholder = placeholder,
    textStyle = ChatTextStyle,
    shape = CustomTextFieldDefault.shape,
    colors = CustomTextFieldDefault.colors
  )
}