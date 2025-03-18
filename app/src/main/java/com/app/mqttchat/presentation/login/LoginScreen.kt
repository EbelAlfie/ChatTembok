package com.app.mqttchat.presentation.login

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import com.app.mqttchat.R
import com.app.mqttchat.domain.model.UserModel
import com.app.mqttchat.presentation.common.UiState
import com.app.mqttchat.presentation.common.UiState.Loaded
import com.app.mqttchat.presentation.login.component.AppTitle
import com.app.mqttchat.presentation.navigation.ScreenRoutes
import com.app.mqttchat.presentation.ui.component.GeneralButton
import com.app.mqttchat.presentation.ui.component.TextInput

@Composable
fun LoginScreen(
  viewModel: LoginViewModel = hiltViewModel(),
  navController: NavHostController
) {
  val uiState by viewModel.loginState.collectAsStateWithLifecycle()
  val loginState by viewModel.apiState.collectAsStateWithLifecycle()
  
  Scaffold(
    modifier = Modifier.fillMaxSize()
  ) {
    Column(
      modifier = Modifier
        .fillMaxSize()
        .padding(it)
        .padding(horizontal = 12.dp),
      verticalArrangement = Arrangement.Center,
      horizontalAlignment = Alignment.CenterHorizontally
    ) {
      AppTitle()
      Spacer(modifier = Modifier.height(40.dp))
      TextInput(
        modifier = Modifier.fillMaxWidth().padding(horizontal = 5.dp),
        value = uiState.username,
        onValueChange = viewModel::onNameChange,
        placeholder = stringResource(R.string.placeholder_text_name)
      )
      Spacer(modifier = Modifier.height(10.dp))
      TextInput(
        modifier = Modifier.fillMaxWidth().padding(horizontal = 5.dp),
        value = uiState.password,
        onValueChange = viewModel::onPasswordChange,
        placeholder = stringResource(R.string.placeholder_text_password),
        visualTransformation = remember { PasswordVisualTransformation() }
      )
      Spacer(Modifier.height(10.dp))
      GeneralButton(
        modifier = Modifier.fillMaxWidth().padding(horizontal = 5.dp),
        onClick = viewModel::login,
        content = { Text(text = stringResource(R.string.login)) }
      )
    }
  }

  LaunchedEffect(loginState) {
    if (loginState is UiState.Loaded)
      navController.navigate(ScreenRoutes.HomeRoute.navigate((loginState as Loaded<UserModel>).data))
  }
}