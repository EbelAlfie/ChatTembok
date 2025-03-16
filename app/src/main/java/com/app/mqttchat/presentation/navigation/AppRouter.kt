package com.app.mqttchat.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.app.mqttchat.presentation.navigation.routes.ScreenRoutes.LoginScreen

@Composable
fun AppRouter(
  navController: NavHostController = rememberNavController()
) {
  NavHost(
    navController = navController,
    startDestination = LoginScreen.route
  ) {
    composable() {

    }

    composable() {

    }
  }
}