package com.app.realtime.api

import com.app.core.ApiResult
import com.app.realtime.config.ConnectionConfig
import com.app.realtime.model.PublishRequest
import com.app.realtime.model.SubscribeRequest
import com.hivemq.client.internal.util.InetSocketAddressUtil
import com.hivemq.client.mqtt.MqttClient
import com.hivemq.client.mqtt.datatypes.MqttQos
import com.hivemq.client.mqtt.datatypes.MqttQos.EXACTLY_ONCE
import com.hivemq.client.mqtt.mqtt5.Mqtt5Client
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RealtimeApiClient @Inject constructor() {
  private var client: Mqtt5Client? = null

  fun connect(config: ConnectionConfig = ConnectionConfig.defaultConfig()) =
    callbackFlow {
      trySend(ApiResult.Loading)
      try {
        with(config) {
          val mqttClient = MqttClient.builder()
            .identifier(clientId)
            .serverAddress(InetSocketAddressUtil.create(host, port))
            .addConnectedListener {
              println("VIS LOG on connect ${it}")
              trySend(ApiResult.Success(true))
            }
            .addDisconnectedListener {
              println("VIS LOG on disconnect ${it.cause}")
              trySend(ApiResult.Error(it.cause))
            }
            .useMqttVersion5()
            .buildAsync()

          mqttClient.connect().whenComplete { t, u ->
            println("VIS LOG CONNECT $t err: $u")
            trySend(ApiResult.Success(true))
          }

          client = mqttClient
          awaitClose {  }
        }
      } catch (e: Throwable) {
        println("VIS LOG Error connect $e")
        trySend(ApiResult.Error(e))
      }
    }

  fun publish(request: PublishRequest) {
    with (request) {
      val qosValue = MqttQos.fromCode(qos.code()) ?: MqttQos.AT_MOST_ONCE
      try {
        client?.also {
          it.toAsync()
            .publishWith()
            .topic(topic)
            .qos(qosValue)
            .payload(message.toByteArray())
            .send()
            .whenComplete { t, u ->
              println("VIS LOG publish $t error: $u")
            }
        }
      } catch (e: Throwable) {
        println("VIS LOG ERROR PUBLISH $e")
      }
    }
  }

  fun subscribe(request: SubscribeRequest): Flow<String> {
    return flow {
      with (request) {
        val qosValue = MqttQos.fromCode(qos.code()) ?: EXACTLY_ONCE
        try {
          client?.also {
            it.toAsync().subscribeWith()
              .topicFilter(topic)
              .qos(qosValue)
              .callback {
                println("VIS LOG Subscribe ${it.topic} ${it.payload}")
              }.send()
          }
        } catch (ex: Throwable) {
          ex.printStackTrace()
          println("VIS LOG ERRROR $ex")
        }
      }
    }
  }

}