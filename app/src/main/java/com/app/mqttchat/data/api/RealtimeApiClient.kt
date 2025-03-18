package com.app.mqttchat.data.api

import com.app.mqttchat.data.model.general.ApiResult
import com.google.gson.Gson
import com.hivemq.client.internal.util.InetSocketAddressUtil
import com.hivemq.client.mqtt.MqttClient
import com.hivemq.client.mqtt.MqttGlobalPublishFilter
import com.hivemq.client.mqtt.datatypes.MqttQos
import com.hivemq.client.mqtt.datatypes.MqttQos.EXACTLY_ONCE
import com.hivemq.client.mqtt.mqtt5.Mqtt5Client
import com.hivemq.client.mqtt.mqtt5.message.connect.connack.Mqtt5ConnAck
import com.hivemq.client.mqtt.mqtt5.message.publish.Mqtt5Publish
import com.hivemq.client.mqtt.mqtt5.message.subscribe.Mqtt5Subscribe
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.future.await
import java.util.UUID
import javax.inject.Inject

class RealtimeApiClient @Inject constructor() {

  private val host = "10.4.77.35"
  private val port = 1883
  private val brokerUrl = "tcp://$host:$port"

  private var client: Mqtt5Client? = null
  private val clientId: String = UUID.randomUUID().toString()

  private val messageChannel = MutableStateFlow("")

  fun connect() = flow<ApiResult<Mqtt5ConnAck>> {
    emit(ApiResult.Loading)
    try {
      val clientBuilder = MqttClient.builder()
        .identifier(clientId)
        .serverAddress(InetSocketAddressUtil.create(host, port))

      client = clientBuilder.useMqttVersion5().buildAsync().also { mqttClient ->
        println("VIS LOG connect")
        val connection = mqttClient.connect().await()
        emit(ApiResult.Success(connection))
      }
    } catch (e: Throwable) {
      emit(ApiResult.Error(e))
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

  fun listen(topic: String): Flow<String> {
    return flow {
      try {
        val topics = arrayOf(topic, clientId)
        val qos = intArrayOf(1, 1)
        val subscribeMessage = Mqtt5Subscribe.builder()
          .topicFilter(topic)
          .qos(EXACTLY_ONCE)
          .build()
        client?.toAsync()?.also {
          it.publishes(MqttGlobalPublishFilter.SUBSCRIBED) {
            println("VIS LOG MESSAGE ${it}")
          }
          it.subscribe(subscribeMessage).whenComplete { mqtt5SubAck, error ->
            println("VIS LOG $error")
          }
        }
      } catch (ex: Throwable) {
        ex.printStackTrace()
      }
    }
  }

  private fun onMessage(message: Mqtt5Publish) {
    println("VIS LOG ${message.payload}")
  }

}