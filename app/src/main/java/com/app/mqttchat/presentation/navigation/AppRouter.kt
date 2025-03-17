package com.app.mqttchat.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.app.mqttchat.presentation.chat.ChatRoomScreen
import com.app.mqttchat.presentation.login.LoginScreen
import com.app.mqttchat.presentation.navigation.routes.ScreenRoutes.ChatRoute
import com.app.mqttchat.presentation.navigation.routes.ScreenRoutes.LoginRoute

@Composable
fun AppRouter(
  navController: NavHostController = rememberNavController()
) {
  NavHost(
    navController = navController,
    startDestination = LoginRoute.route
  ) {
    composable(
      route = LoginRoute.route
    ) {
      LoginScreen()
    }

    composable(
      route = ChatRoute.route,
      arguments = ChatRoute.navArguments
    ) {
      ChatRoomScreen()
    }
  }
}