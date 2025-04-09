package com.app.mqttchat.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.core.os.BundleCompat
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.app.mqttchat.domain.model.UserModel
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
      val user = it.arguments?.let { bundle -> BundleCompat.getParcelable(bundle,"user", UserModel::class.java) }
      HomeScreen(navController = navController, user = user)
    }

    composable(
      route = ChatRoute.route,
      arguments = ChatRoute.navArguments
    ) {
      val roomId = it.arguments?.getString("roomId") ?: ""
      ChatRoomScreen(roomId = roomId, navController = navController)
    }
  }
}