package com.giedrius.slikas.pizzaratings.compose

import androidx.compose.foundation.layout.Column
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.giedrius.slikas.pizzaratings.data.model.Rating

@Composable
fun ListItem(
  rating: Rating
) {
  Column {
    Text(rating.name)
    Text(rating.addresses.toString())
    Text("${rating.averageRating} average rating")
  }
}

@Preview(showSystemUi = true)
@Composable
fun PreviewListItem() {
  ListItem(
    Rating(
      name = "Pizza & Nachos Pub",
      addresses = listOf("Linkmenų g.4, Vilnius 09300"),
      ratings = mapOf(
        "gsli" to 3
      ),
      numberOfRatings = 1,
      averageRating = 3.0
    )
  )
}