package com.app.mqttchat.presentation.home

import androidx.annotation.DrawableRes
import com.app.mqttchat.R

sealed class SubPage(
  val label: String = "",
  @DrawableRes val icon: Int
) {
  data object ChatListPage: SubPage(label = "Chat", icon = R.drawable.ic_chat_menu)
  data object ProfilePage: SubPage(label = "Profile", icon = R.drawable.ic_setting)

  companion object {
    fun getHomePages() = listOf(ChatListPage, ProfilePage)
  }
}