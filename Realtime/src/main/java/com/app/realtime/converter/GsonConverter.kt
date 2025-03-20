package com.app.realtime.converter

//
//class GsonConverter<type>(
//  private val gson: Gson
//) : MessageTypeConverter<type> {
//
//  override fun fromMessage(topic: String, message: type): String {
//    val adapter: TypeAdapter<type> = gson.getAdapter(TypeToken.get(type))
//  }
//
//  override fun toMessage(topic: String, message: String): type {
//    val adapter: TypeAdapter<type> = gson.getAdapter(TypeToken.get(type))
//  }
//
//}