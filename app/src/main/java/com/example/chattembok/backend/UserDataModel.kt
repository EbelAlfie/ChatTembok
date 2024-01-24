package com.example.chattembok.backend

import android.os.Parcelable
import com.example.chattembok.backend.PRIVILEDGE.ADMIN
import com.example.chattembok.backend.PRIVILEDGE.CUSTOMER
import kotlinx.parcelize.Parcelize

@Parcelize
data class UserDataModel (
  val priviledge: PRIVILEDGE,
  val userId: String,
  val userName: String
): Parcelable

enum class PRIVILEDGE {
  CUSTOMER,
  ADMIN
}

//ceritanya JSON
object DUMMYDATA {
  val custList = arrayListOf(
    UserDataModel(
      priviledge = CUSTOMER,
      userId = "nematoda",
      userName = "cacing tanah"
    ),
    UserDataModel(
      priviledge = CUSTOMER,
      userId = "cachingtanah",
      userName = "caching tanah"
    )
  )

  val storeUserList = arrayListOf(
    UserDataModel(
      priviledge = ADMIN,
      userId = "k446",
      userName = "Alfamart Cikokol"
    ),
    UserDataModel(
      priviledge = ADMIN,
      userId = "6969",
      userName = "Alfamart Antartika"
    ),
    UserDataModel(
      priviledge = ADMIN,
      userId = "usa29",
      userName = "Alfamart USA"
    )
  )
}