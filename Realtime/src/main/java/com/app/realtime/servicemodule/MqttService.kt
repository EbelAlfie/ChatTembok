package com.app.realtime.servicemodule

import com.app.core.ApiResult
import com.app.realtime.Utils.isWebSocketScheme
import com.app.realtime.config.ConnectionConfig
import com.app.realtime.config.Qos
import com.app.realtime.interceptor.RealtimeInterceptor
import com.app.realtime.model.RealtimeMessage
import com.app.realtime.model.SubscribeRequest
import com.app.realtime.model.UninitializedClientException
import com.app.realtime.service.PacketMapper
import com.app.realtime.service.RealtimeService
import com.hivemq.client.internal.util.InetSocketAddressUtil
import com.hivemq.client.mqtt.MqttClient
import com.hivemq.client.mqtt.MqttWebSocketConfig
import com.hivemq.client.mqtt.datatypes.MqttQos
import com.hivemq.client.mqtt.datatypes.MqttQos.EXACTLY_ONCE
import com.hivemq.client.mqtt.datatypes.MqttTopic
import com.hivemq.client.mqtt.mqtt5.Mqtt5Client
import com.hivemq.client.mqtt.mqtt5.Mqtt5ClientConfig
import com.hivemq.client.mqtt.mqtt5.advanced.interceptor.Mqtt5ClientInterceptors
import com.hivemq.client.mqtt.mqtt5.advanced.interceptor.qos2.Mqtt5IncomingQos2Interceptor
import com.hivemq.client.mqtt.mqtt5.advanced.interceptor.qos2.Mqtt5OutgoingQos2Interceptor
import com.hivemq.client.mqtt.mqtt5.message.connect.Mqtt5Connect
import com.hivemq.client.mqtt.mqtt5.message.publish.Mqtt5Publish
import com.hivemq.client.mqtt.mqtt5.message.publish.pubcomp.Mqtt5PubComp
import com.hivemq.client.mqtt.mqtt5.message.publish.pubcomp.Mqtt5PubCompBuilder
import com.hivemq.client.mqtt.mqtt5.message.publish.pubrec.Mqtt5PubRec
import com.hivemq.client.mqtt.mqtt5.message.publish.pubrec.Mqtt5PubRecBuilder
import com.hivemq.client.mqtt.mqtt5.message.publish.pubrel.Mqtt5PubRel
import com.hivemq.client.mqtt.mqtt5.message.publish.pubrel.Mqtt5PubRelBuilder
import com.hivemq.client.mqtt.mqtt5.message.subscribe.Mqtt5Subscribe
import com.hivemq.client.mqtt.mqtt5.message.unsubscribe.Mqtt5Unsubscribe
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import java.util.concurrent.Executors
import java.util.concurrent.ThreadFactory
import kotlin.jvm.optionals.getOrNull

class MqttService(
  private val interceptors: MutableList<RealtimeInterceptor>
) : RealtimeService {
  private var client: Mqtt5Client? = null

  override fun connect(config: ConnectionConfig) =
    callbackFlow {
      trySend(ApiResult.Loading)
      try {
        with(config) {
          var mqttBuilder = MqttClient.builder()
            .useMqttVersion5()
            .identifier(clientId)
            .serverAddress(InetSocketAddressUtil.create(host, port ?: 0))
            .addDisconnectedListener {
              println("VIS LOG disconnected ${it.cause}")
              trySend(ApiResult.Error(it.cause))
            }

          if (isWebSocketScheme(scheme))
            mqttBuilder = mqttBuilder.webSocketConfig(MqttWebSocketConfig.builder().subprotocol("mqtt").serverPath("/mqtt").build())

          interceptors.forEach {
            mqttBuilder = mqttBuilder
              .advancedConfig()
              .interceptors(adaptInterceptor(it))
              .applyAdvancedConfig()
          }

          val mqttClient = mqttBuilder.buildAsync()

          val connectPacketBuilder = Mqtt5Connect
            .builder()
            .keepAlive(60)
            .build()

          mqttClient.connect(connectPacketBuilder)
            .whenComplete { t, u ->
              if (t != null) trySend(ApiResult.Success(true))
              if (u != null) trySend(ApiResult.Error(u.initCause(u.cause)))
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

  override fun publish(message: RealtimeMessage) {
    try {
      with(message) {
        if (client == null) throw UninitializedClientException()

        val publishMessage = Mqtt5Publish.builder()
          .topic(topic)
          .qos(qos.getQosCode())
          .retain(retained)
          .payload(this.message)
          .build()

        client?.toBlocking()?.publish(publishMessage)
      }
    } catch (e: Throwable) {
      println("VIS LOG ERROR PUBLISH $e")
    }
  }

  override fun subscribe(request: SubscribeRequest): Flow<RealtimeMessage> {
    return callbackFlow {
      with(request) {
        try {
          if (client == null) throw UninitializedClientException()

          val subscribeMessage = Mqtt5Subscribe.builder()
            .topicFilter(topic)
            .qos(qos.getQosCode())
            .build()

          client?.toAsync()?.subscribe(subscribeMessage, { mqttMessage ->
            val realtimeMessage = toRealtimeMessage(mqttMessage)
            trySend(realtimeMessage)
          }, Executors.newSingleThreadExecutor(object: ThreadFactory {
            override fun newThread(r: Runnable?): Thread {
              return Thread(r, "HIVE MQ Subscribe")
            }
          })) ?.whenComplete { subAck, error ->
              println("VIS LOG suback ${subAck.reasonString}")
              interceptors.forEach { interceptor ->
                interceptor.onSubscribeAck(PacketMapper.onSubscribeAck(subAck))
              }
            }
        } catch (ex: Throwable) {
          ex.printStackTrace()
          println("VIS LOG ERRROR $ex")
        } finally {
          awaitClose { }
        }
      }
    }
  }

  override fun unsubscribe() {
    client?.also {
      val unsubscribeRequest = Mqtt5Unsubscribe.builder()
        .topicFilter("all")
        .build()

      it.toAsync().unsubscribe(unsubscribeRequest)
    }
  }

  override fun disconnect() {
    client?.also { it.toAsync().disconnect() }
  }

  //Converters
  private fun Qos.getQosCode() = MqttQos.fromCode(code()) ?: EXACTLY_ONCE

  private fun toRealtimeMessage(message: Mqtt5Publish) = RealtimeMessage(
    topic = message.topic.levels.joinToString(MqttTopic.TOPIC_LEVEL_SEPARATOR.toString()),
    message = message.payload.getOrNull(),
    qos = Qos.fromCode(message.qos.code),
    retained = message.isRetain
  )

  private fun adaptInterceptor(interceptor: RealtimeInterceptor?) =
    Mqtt5ClientInterceptors.builder()
      .incomingQos1Interceptor { clientConfig, publish, pubAckBuilder ->
        println("VIS LOG incomingQos1Interceptor ${publish} d ${pubAckBuilder}")
        interceptor?.onPublish(PacketMapper.toPublish(publish))
      }
      .incomingQos2Interceptor(object : Mqtt5IncomingQos2Interceptor {
        override fun onPublish(
          clientConfig: Mqtt5ClientConfig,
          publish: Mqtt5Publish,
          pubRecBuilder: Mqtt5PubRecBuilder
        ) {
          println("VIS LOG incomingQos1Interceptor onPublish ${publish} d ${pubRecBuilder}")
          interceptor?.onPublish(PacketMapper.toPublish(publish))
        }

        override fun onPubRel(
          clientConfig: Mqtt5ClientConfig,
          pubRel: Mqtt5PubRel,
          pubCompBuilder: Mqtt5PubCompBuilder
        ) {
          println("VIS LOG incomingQos1Interceptor onPubRel ${pubRel} d ${pubCompBuilder}")
          interceptor?.onPublishRel(PacketMapper.toPublishRel(pubRel))
        }

      })
      .outgoingQos1Interceptor { clientConfig, publish, pubAck ->
        println("VIS LOG outgoingQos1Interceptor ${publish} d ${pubAck}")
        interceptor?.onPublishAck(PacketMapper.toPublishAck(publish, pubAck))
      }
      .outgoingQos2Interceptor(object : Mqtt5OutgoingQos2Interceptor {
        override fun onPubRec(
          clientConfig: Mqtt5ClientConfig,
          publish: Mqtt5Publish,
          pubRec: Mqtt5PubRec,
          pubRelBuilder: Mqtt5PubRelBuilder
        ) {
          println("VIS LOG outgoingQos2Interceptor onPubRec ${publish} d ${pubRec} p ${pubRelBuilder}")
          interceptor?.onPublishRec(PacketMapper.toPublishRec(publish, pubRec))
        }

        override fun onPubRecError(
          clientConfig: Mqtt5ClientConfig,
          publish: Mqtt5Publish,
          pubRec: Mqtt5PubRec
        ) {
          println("VIS LOG outgoingQos2Interceptor onPubRecError ${publish} d ${pubRec}")
        }

        override fun onPubComp(
          clientConfig: Mqtt5ClientConfig,
          pubRel: Mqtt5PubRel,
          pubComp: Mqtt5PubComp
        ) {
          println("VIS LOG outgoingQos2Interceptor onPubComp ${pubRel} d ${pubComp}")
          interceptor?.onPublishComp(PacketMapper.toPublishComp(pubRel, pubComp))
        }

      })
      .build()
}