package com.example.chattembok.backend.orderrouter

import com.example.chattembok.presentation.orderhistory.model.OrderModel
import kotlin.random.Random

object Order {

  private fun generateRandomName(): String {
    return when (Random.nextInt(0, 3)) {
      0 -> "Alfamart Cikokol"
      1 -> "Alfamart Antartika"
      else -> "Alfamart USA"
    }

  }

  private fun generateRandomOrderNo(): String {
    val id = Random.nextLong()
    return "S-${id}"
  }

  private fun generateRandomProduct(): String {
    val id = Random.nextInt(0, 20)
    return when (id) {
      1 -> "Botol"
      2 -> "Aqua"
      3 -> "Nikke"
      4 -> "Air Jorban"
      5 -> "Abidas"
      6 -> "Juice Ropopo"
      7 -> "Panta"
      8 -> "Tiket antartika"
      9 -> "sekop"
      10 -> "palu"
      11 -> "garpu"
      12 -> "pisau hilt"
      13 -> "Rumah di PIK"
      14 -> "Rumah di Bali"
      15 -> "Alfatower"
      16 -> "Saham alfamart"
      17 -> "Saham indomart"
      18 -> "Dogge coin"
      19 -> "Apa yaa"
      else -> "Else"
    }
  }

  fun generateRandomOrder() =
    OrderModel(
      productName = generateRandomProduct(),
      sellerName = generateRandomName(),
      nomorShipment = generateRandomOrderNo()
    )
}