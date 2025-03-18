package com.app.mqttchat.presentation.home.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.app.mqttchat.presentation.home.SubPage

@Composable
fun NavigationBar(
  subPages: List<SubPage>,
  selectedIndex: Int,
  onIconClick: (Int) -> Unit
) {
  BottomAppBar(
    contentPadding = PaddingValues(horizontal = 10.dp, vertical = 8.dp)
  ) {
    Row(
      modifier = Modifier.fillMaxWidth(),
      horizontalArrangement = Arrangement.SpaceBetween
    ) {
      subPages.forEachIndexed { index, item ->
        NavigationIcon(
          modifier = Modifier
            .width(IntrinsicSize.Max)
            .clickable { onIconClick(index) },
          page = item,
          isSelected = selectedIndex == index
        )
      }
    }
  }
}

@Composable
fun NavigationIcon(
  modifier: Modifier = Modifier,
  page: SubPage,
  isSelected: Boolean
) {
  Column(
    modifier = modifier,
    verticalArrangement = Arrangement.spacedBy(5.dp)
  ) {
    Icon(
      painter = painterResource(page.icon),
      tint = if (isSelected) Color.Red else Color.Black,
      contentDescription = null
    )
    Text(
      text = page.label,
      color = if (isSelected) Color.Red else Color.Black
    )
  }
}