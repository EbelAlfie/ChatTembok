package com.app.mqttchat.presentation.login

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.app.mqttchat.presentation.ui.component.TextInput

@Composable
fun LoginScreen(
  viewModel: LoginViewModel = hiltViewModel()
) {
  Scaffold {
    Column(
      modifier = Modifier.padding(it),
      verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
      TextInput()
      TextInput()
    }
  }
}