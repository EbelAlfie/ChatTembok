package com.app.mqttchat.presentation.login

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.app.mqttchat.R
import com.app.mqttchat.presentation.ui.component.GeneralButton
import com.app.mqttchat.presentation.ui.component.TextInput

@Composable
fun LoginScreen(
  viewModel: LoginViewModel = hiltViewModel()
) {
  val uiState by viewModel.loginState.collectAsStateWithLifecycle()
  Scaffold(
    modifier = Modifier.fillMaxSize()
  ) {
    Column(
      modifier = Modifier.fillMaxSize().padding(it),
      verticalArrangement = Arrangement.Center,
      horizontalAlignment = Alignment.CenterHorizontally
    ) {
      TextInput(
        value = uiState.username,
        onValueChange = viewModel::onNameChange,
        placeholder = stringResource(R.string.placeholder_text_name)
      )
      Spacer(modifier = Modifier.height(10.dp))
      TextInput(
        value = uiState.password,
        onValueChange = viewModel::onPasswordChange,
        placeholder = stringResource(R.string.placeholder_text_password)
      )
      Spacer(Modifier.height(10.dp))
      GeneralButton(
        onClick = viewModel::login,
        content = {
          Text(text = "Login")
        }
      )
    }
  }
}