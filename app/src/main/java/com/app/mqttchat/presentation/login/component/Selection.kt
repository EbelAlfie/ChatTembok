package com.app.mqttchat.presentation.login.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.app.mqttchat.presentation.login.component.Network.MQTT
import com.app.mqttchat.presentation.login.component.Network.MQTT_WS
import com.app.mqttchat.presentation.login.component.Network.WS
import com.app.mqttchat.presentation.ui.component.TextInput

enum class Network {
  MQTT,
  WS,
  MQTT_WS;

  companion object {
    fun getScheme(network: Network) = when (network) {
        MQTT -> "tcp"
        MQTT_WS,
        WS -> "ws"
      }

  }
}

@Composable
fun Selection(
  networkState: Network = MQTT,
  host: String,
  onSelected: (Network) -> Unit,
  onHostUpdated: (String) -> Unit
) {
  Column(
    modifier = Modifier.fillMaxWidth(),
    verticalArrangement = Arrangement.spacedBy(5.dp),
    horizontalAlignment = Alignment.CenterHorizontally
  ) {
    Row(horizontalArrangement = Arrangement.SpaceBetween) {
      Connection(
        selected = networkState == MQTT,
        onSelected = { onSelected(MQTT) },
        text = "Mqtt"
      )

      Connection(
        selected = networkState == WS,
        onSelected = { onSelected(WS) },
        text = "ws"
      )

      Connection(
        selected = networkState == MQTT_WS,
        onSelected = { onSelected(MQTT_WS) },
        text = "Mqtt over Ws"
      )
    }

    TextInput(
      modifier = Modifier.fillMaxWidth(),
      value = host,
      onValueChange = onHostUpdated
    )
  }
}

@Composable
fun Connection(
  selected: Boolean,
  onSelected: () -> Unit,
  text: String
) {
  Column(
    verticalArrangement = Arrangement.SpaceAround,
    horizontalAlignment = Alignment.CenterHorizontally
  ) {
    RadioButton(
      selected = selected,
      onClick = onSelected
    )
    Text(text = text)
  }
}