package com.app.mqttchat.presentation.navigation.routes

import androidx.navigation.NamedNavArgument
import androidx.navigation.NavType
import androidx.navigation.navArgument

sealed class ScreenRoutes(
  val route: String,
  val navArguments: List<NamedNavArgument> = emptyList()
) {

  data object LoginRoute: ScreenRoutes(route = "login")

  data object ChatRoute: ScreenRoutes(
    route = "chat_room",
    navArguments = listOf(navArgument("roomId") { type = NavType.StringType })
  ) {
    fun createRoute() = route
  }

}