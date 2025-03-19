package com.app.mqttchat.data.api

import com.app.mqttchat.data.model.general.ApiResult
import com.hivemq.client.internal.util.InetSocketAddressUtil
import com.hivemq.client.mqtt.MqttClient
import com.hivemq.client.mqtt.datatypes.MqttQos
import com.hivemq.client.mqtt.datatypes.MqttQos.EXACTLY_ONCE
import com.hivemq.client.mqtt.mqtt5.Mqtt5Client
import com.hivemq.client.mqtt.mqtt5.message.connect.connack.Mqtt5ConnAck
import com.hivemq.client.mqtt.mqtt5.message.publish.Mqtt5Publish
import com.hivemq.client.mqtt.mqtt5.message.subscribe.Mqtt5Subscribe
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.runBlocking
import java.util.UUID
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RealtimeApiClient @Inject constructor() {
  private val host = "10.4.77.103"
  private val port = 1883
  private val brokerUrl = "tcp://$host:$port"

  private var client: Mqtt5Client? = null
  private val clientId: String = UUID.randomUUID().toString()

  fun connect() = flow<ApiResult<Mqtt5ConnAck>> {
    emit(ApiResult.Loading)
    try {
      val mqttClient = MqttClient.builder()
        .identifier(clientId)
        .serverAddress(InetSocketAddressUtil.create(host, port))
        .addConnectedListener {
          println("VIS LOG on connect ${it}")
        }
        .addDisconnectedListener {
          println("VIS LOG on disconnect ${it.cause}")
        }
        .useMqttVersion5()
        .buildAsync()

      println("VIS LOG MQTT")

      mqttClient.connect().whenComplete { t, u ->
        println("VIS LOG CONNECT $t err: $u")
        runBlocking { emit(ApiResult.Success(t)) }
      }

      client = mqttClient

    } catch (e: Throwable) {
      println("VIS LOG Error connect $e")
      emit(ApiResult.Error(e))
    }
  }

  fun send(
    topic: String,
    message: String
  ) {
    try {
      client?.also {
        it.toAsync()
          .publishWith()
          .topic(topic)
          .qos(MqttQos.AT_LEAST_ONCE)
          .payload(message.toByteArray())
          .send()
          .whenComplete { t, u ->
            println("VIS LOG publish $t error: $u")
          }
        println("VIS LOG publish to $topic")
      }
    } catch (e: Throwable) {
      println("VIS LOG ERROR PUBLISH $e")
    }
  }

  fun listen(topic: String): Flow<String> {
    return flow {
      try {
        val subscribeMessage = Mqtt5Subscribe.builder()
          .topicFilter(topic)

          .build()

        client?.also {
          println("VIS LOG client subs")
          it.toAsync().subscribeWith()
            .topicFilter(topic)
            .qos(EXACTLY_ONCE)
            .callback {
              println("VIS LOG Subscribe ${it.topic} ${it.payload}")
            }.send()
//
//          it.publishes(MqttGlobalPublishFilter.SUBSCRIBED) {
//            println("VIS LOG message from topic ${it.topic} ${it}")
//          }
//          it.subscribe(subscribeMessage).whenComplete { mqtt5SubAck, error ->
//            println("VIS LOG $error")
//          }
          println("VIS LOG subscribing topic $topic")
        }
      } catch (ex: Throwable) {
        ex.printStackTrace()
        println("VIS LOG ERRROR $ex")
      }
    }
  }

  private fun onMessage(message: Mqtt5Publish) {
    println("VIS LOG ${message.payload}")
  }

}