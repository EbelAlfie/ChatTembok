package com.app.mqttchat.presentation.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import com.app.mqttchat.presentation.chatlist.ChatListScreen
import com.app.mqttchat.presentation.home.SubPage.ChatListPage
import com.app.mqttchat.presentation.home.component.NavigationBar
import com.app.mqttchat.presentation.ui.component.ConnectionLoading

@Composable
fun HomeScreen(
  viewModel: HomeViewModel = hiltViewModel(),
  navController: NavHostController
) {
  val subPages by remember { mutableStateOf(SubPage.getHomePages()) }
  var selectedIndex by remember { mutableIntStateOf(0) }

  val connectionStatus by viewModel.connectionStatus.collectAsStateWithLifecycle()

  LaunchedEffect(Unit) { viewModel.establishRealtimeConnection()  }

  ConnectionLoading(connectionStatus)

  Scaffold(
    bottomBar = {
      NavigationBar(
        subPages = subPages,
        selectedIndex = selectedIndex,
        onIconClick = { selectedIndex = it }
      )
    }
  ) {
    Column(
      modifier = Modifier.padding(it)
    ) {
      when(subPages[selectedIndex]) {
        is ChatListPage -> ChatListScreen(navController)
        else -> {}
      }
    }
  }
}