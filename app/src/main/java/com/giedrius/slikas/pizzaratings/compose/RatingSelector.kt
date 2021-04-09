package com.giedrius.slikas.pizzaratings.compose

import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun RatingSelector(
  onRatingClicked: (Int) -> Unit
) {
  Column(modifier = Modifier.padding(top = 8.dp)) {
    Row(
      modifier = Modifier.align(Alignment.CenterHorizontally)
    ) {
      Button(onClick = { onRatingClicked(1) }) {
        Text(text = "1")
      }
      Spacer(modifier = Modifier.width(4.dp))
      Button(onClick = { onRatingClicked(2) }) {
        Text(text = "2")
      }
      Spacer(modifier = Modifier.width(4.dp))
      Button(onClick = { onRatingClicked(3) }) {
        Text(text = "3")
      }
      Spacer(modifier = Modifier.width(4.dp))
      Button(onClick = { onRatingClicked(4) }) {
        Text(text = "4")
      }
      Spacer(modifier = Modifier.width(4.dp))
      Button(onClick = { onRatingClicked(5) }) {
        Text(text = "5")
      }
    }
  }
}

@Preview(showSystemUi = true)
@Composable
fun PreviewRatingSelector() {
  RatingSelector(
    onRatingClicked = {}
  )
}