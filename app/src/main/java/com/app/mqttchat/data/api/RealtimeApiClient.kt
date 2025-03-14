package com.app.mqttchat.data.api

import com.hivemq.client.internal.util.InetSocketAddressUtil
import com.hivemq.client.mqtt.MqttClient
import com.hivemq.client.mqtt.datatypes.MqttQos
import com.hivemq.client.mqtt.mqtt5.Mqtt5Client
import java.util.UUID

class RealtimeApiClient {

  private val host = "10.4.77.35"
  private val port = 1883
  private val brokerUrl = "tcp://$host:$port"

  private var client: Mqtt5Client? = null
  private val clientId: String = UUID.randomUUID().toString()

  fun connect() {
    try {
      val clientBuilder = MqttClient.builder()
        .identifier(clientId)
        .serverAddress(InetSocketAddressUtil.create(host, port))

      client = clientBuilder.useMqttVersion5().buildAsync().also { mqttClient ->
        println("VIS LOG connect")
        val connection = mqttClient.connect().await()
        emit(ResponseResult.Success(connection))
      }
    } catch (e: Throwable) {
      emit(ResponseResult.Error(e))
    }
  }

  fun send(
    topic: String,
    message: String
  ) {
    val gson = Gson()
    val msg = gson.toJson(message)
    val qos = 1
    val retained = false
    println("VIS LOG PUBLISH")
    try {
      client?.also {
        it.toAsync()
          .publishWith()
          .topic(topic)
          .qos(MqttQos.AT_LEAST_ONCE)
          .payload(msg.toByteArray())
          .send()
      }
      println("VIS LOG MESSAGE PUBLISHED")
    } catch (e: Throwable) {
      println("VIS LOG ERROR PUBLISH $e")
    }
  }

  fun listen(topic: String) {
    try {

    } catch (error: Throwable) {

    }
  }

}