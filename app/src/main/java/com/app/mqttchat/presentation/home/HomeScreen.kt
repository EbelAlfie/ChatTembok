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
import androidx.navigation.NavHostController
import com.app.mqttchat.presentation.home.component.NavigationBar

@Composable
fun HomeScreen(
  viewModel: HomeViewModel = hiltViewModel(),
  navController: NavHostController
) {
  val subPages by remember { mutableStateOf(SubPage.getHomePages()) }
  var selectedIndex by remember { mutableIntStateOf(0) }

  LaunchedEffect(Unit) { viewModel.establishRealtimeConnection()  }

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

    }
  }
}