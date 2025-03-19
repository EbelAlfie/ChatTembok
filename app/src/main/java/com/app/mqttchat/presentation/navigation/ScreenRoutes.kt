package com.app.mqttchat.presentation.navigation

import androidx.navigation.NamedNavArgument
import androidx.navigation.NavType
import androidx.navigation.navArgument
import com.app.mqttchat.domain.model.UserModel
import com.app.mqttchat.presentation.navigation.argument.UserData
import com.app.mqttchat.presentation.navigation.argument.putArgument
import com.app.mqttchat.presentation.navigation.argument.putParcelable

sealed class ScreenRoutes(
  private val baseRoute: String,
  val navArguments: List<NamedNavArgument> = emptyList()
) {
  val route: String
    get() {
      val mandatoryArguments = navArguments.filter { it.argument.defaultValue == null }
        .takeIf { it.isNotEmpty() }
        ?.joinToString(separator = "/", prefix = "/") { "{${it.name}}" } ?: ""

      val optionalArguments = navArguments.filter { it.argument.defaultValue != null }
        .takeIf { it.isNotEmpty() }
        ?.joinToString(separator = "&", prefix = "?") { "${it.name}={${it.name}}" } ?: ""
      return "$baseRoute$mandatoryArguments$optionalArguments"
    }

  data object LoginRoute: ScreenRoutes(baseRoute = "login")

  data object ChatRoute: ScreenRoutes(
    baseRoute = "chat_room",
    navArguments = listOf(navArgument("roomId") { type = NavType.StringType })
  ) {
    fun navigate(roomId: String) = route.putArgument("roomId", roomId)
  }

  data object HomeRoute: ScreenRoutes(
    baseRoute = "home",
    navArguments = listOf(navArgument("user") { type = UserData() })
  ) {
    fun navigate(user: UserModel) = route.putParcelable("user", user)
  }

}