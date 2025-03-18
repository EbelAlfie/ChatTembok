package com.app.mqttchat.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.app.mqttchat.presentation.chat.ChatRoomScreen
import com.app.mqttchat.presentation.home.HomeScreen
import com.app.mqttchat.presentation.login.LoginScreen
import com.app.mqttchat.presentation.navigation.ScreenRoutes.ChatRoute
import com.app.mqttchat.presentation.navigation.ScreenRoutes.HomeRoute
import com.app.mqttchat.presentation.navigation.ScreenRoutes.LoginRoute

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
      LoginScreen(navController = navController)
    }

    composable(
      route = HomeRoute.route,
      arguments = HomeRoute.navArguments
    ) {
      HomeScreen(navController = navController)
    }

    composable(
      route = ChatRoute.route,
      arguments = ChatRoute.navArguments
    ) {
      ChatRoomScreen(navController = navController)
    }
  }
}