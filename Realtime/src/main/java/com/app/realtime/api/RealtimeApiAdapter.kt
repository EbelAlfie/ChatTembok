package com.app.realtime.api

import com.app.core.ApiResult
import com.app.realtime.config.ConnectionConfig
import com.app.realtime.config.Qos
import com.app.realtime.model.RealtimeMessage
import com.app.realtime.model.SubscribeRequest
import com.hivemq.client.internal.util.InetSocketAddressUtil
import com.hivemq.client.mqtt.MqttClient
import com.hivemq.client.mqtt.datatypes.MqttQos
import com.hivemq.client.mqtt.datatypes.MqttQos.EXACTLY_ONCE
import com.hivemq.client.mqtt.datatypes.MqttTopic
import com.hivemq.client.mqtt.mqtt5.Mqtt5Client
import com.hivemq.client.mqtt.mqtt5.advanced.Mqtt5ClientAdvancedConfig
import com.hivemq.client.mqtt.mqtt5.advanced.interceptor.Mqtt5ClientInterceptors
import com.hivemq.client.mqtt.mqtt5.advanced.interceptor.Mqtt5ClientInterceptorsBuilder
import com.hivemq.client.mqtt.mqtt5.advanced.interceptor.qos1.Mqtt5IncomingQos1Interceptor
import com.hivemq.client.mqtt.mqtt5.advanced.interceptor.qos1.Mqtt5OutgoingQos1Interceptor
import com.hivemq.client.mqtt.mqtt5.advanced.interceptor.qos2.Mqtt5IncomingQos2Interceptor
import com.hivemq.client.mqtt.mqtt5.advanced.interceptor.qos2.Mqtt5OutgoingQos2Interceptor
import com.hivemq.client.mqtt.mqtt5.message.publish.Mqtt5Publish
import com.hivemq.client.mqtt.mqtt5.message.subscribe.Mqtt5Subscribe
import com.hivemq.client.mqtt.mqtt5.message.unsubscribe.Mqtt5Unsubscribe
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import javax.inject.Inject
import javax.inject.Singleton
import kotlin.jvm.optionals.getOrNull


@Singleton
class RealtimeApiAdapter @Inject constructor() {
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

          mqttClient
            .connectWith()
            .keepAlive(60)
            .send()
            .whenComplete { t, u ->
              println("VIS LOG CONNECT $t err: $u")
              trySend(ApiResult.Success(true))
            }

          client = mqttClient
        }
      } catch (e: Throwable) {
        println("VIS LOG Error connect $e")
        trySend(ApiResult.Error(e))
      } finally {
        awaitClose { }
      }
    }

  fun publish(realtimeMessage: RealtimeMessage) {
    with(realtimeMessage) {
      try {
        client?.also {
          val publishMessage = Mqtt5Publish.builder()
            .topic(topic)
            .qos(qos.getQosCode())
            .retain(retained)
            .payload(message)
            .build()
          it.toAsync().publish(publishMessage)
        }
      } catch (e: Throwable) {
        println("VIS LOG ERROR PUBLISH $e")
      }
    }
  }

  fun subscribe(request: SubscribeRequest): Flow<RealtimeMessage> {
    return callbackFlow {
      with(request) {
        try {
          client?.also {
            val subscribeMessage = Mqtt5Subscribe.builder()
              .topicFilter(topic)
              .qos(qos.getQosCode())
              .build()

            it.toAsync().subscribe(subscribeMessage) { message ->
              val realtimeMessage = RealtimeMessage(
                topic = message.topic.levels.joinToString(MqttTopic.TOPIC_LEVEL_SEPARATOR.toString()),
                message = message.payload.getOrNull(),
                qos = Qos.fromCode(message.qos.code),
                retained = message.isRetain
              )
              trySend(realtimeMessage)
            }
          }
        } catch (ex: Throwable) {
          ex.printStackTrace()
          println("VIS LOG ERRROR $ex")
        } finally {
          awaitClose {  }
        }
      }
    }
  }

  fun unsubscribe() {
    client?.also {
      val unsubscribeRequest = Mqtt5Unsubscribe.builder()
        .topicFilter("all")
        .build()

      it.toAsync().unsubscribe(unsubscribeRequest)
    }
  }

  fun disconnect() {
    client?.also { it.toAsync().disconnect() }
  }

  private fun Qos.getQosCode() = MqttQos.fromCode(code()) ?: EXACTLY_ONCE
}