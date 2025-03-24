package com.app.realtime.servicemodule

import com.app.core.ApiResult
import com.app.realtime.config.ConnectionConfig
import com.app.realtime.config.Qos.AT_MOST_ONCE
import com.app.realtime.model.RealtimeMessage
import com.app.realtime.model.SubscribeRequest
import com.app.realtime.service.RealtimeService
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.callbackFlow
import okhttp3.OkHttpClient.Builder
import okhttp3.Request
import okhttp3.Response
import okhttp3.WebSocket
import okhttp3.WebSocketListener
import okio.ByteString
import okio.ByteString.Companion.toByteString
import java.nio.ByteBuffer
import java.util.concurrent.TimeUnit.SECONDS

class WsService: RealtimeService {
  private var client: WebSocket? = null
  private val _messageEvent = MutableSharedFlow<RealtimeMessage>(replay = 1)

  override fun connect(config: ConnectionConfig): Flow<ApiResult<Boolean>> {
    return callbackFlow {
      val clientBuilder = Builder()
        .readTimeout(60L, SECONDS)
        .retryOnConnectionFailure(true)

      val okHttpClient = clientBuilder.build()

      val request = Request.Builder()
        .url("wss://gtw32lpt-8003.asse.devtunnels.ms/ws")
        .build()

      client = okHttpClient.newWebSocket(
        request = request,
        listener = object: WebSocketListener() {
          override fun onOpen(webSocket: WebSocket, response: Response) {
            super.onOpen(webSocket, response)
            println("VIS LOG on open ${response.message}")
          }
          override fun onMessage(webSocket: WebSocket, text: String) {
            super.onMessage(webSocket, text)
            println("VIS LOG on Message")
            val realtimeMessage = RealtimeMessage(
              topic = "",
              message = ByteBuffer.wrap(text.toByteArray()),
              qos = AT_MOST_ONCE,
              retained = false,
            )
            _messageEvent.tryEmit(realtimeMessage)
          }

          override fun onMessage(webSocket: WebSocket, bytes: ByteString) {
            super.onMessage(webSocket, bytes)
            println("VIS LOG on Message")
            val realtimeMessage = RealtimeMessage(
              topic = "",
              message = bytes.asByteBuffer(),
              qos = AT_MOST_ONCE,
              retained = false,
            )
            _messageEvent.tryEmit(realtimeMessage)
          }

          override fun onFailure(webSocket: WebSocket, t: Throwable, response: Response?) {
            super.onFailure(webSocket, t, response)
            println("VIS LOG on failure ${t.cause}")
          }

          override fun onClosing(webSocket: WebSocket, code: Int, reason: String) {
            super.onClosing(webSocket, code, reason)
            println("VIS LOG on closing ${reason}")
          }
        }
      )
      awaitClose { }
    }
  }

  override fun publish(message: RealtimeMessage) {
    message.message?.toByteString()?.let {
      client?.send(it)
    }
  }

  override fun subscribe(request: SubscribeRequest): Flow<RealtimeMessage> {
    return _messageEvent
  }

  override fun unsubscribe() {
    TODO("Not yet implemented")
  }

  override fun disconnect() {
    TODO("Not yet implemented")
  }
}