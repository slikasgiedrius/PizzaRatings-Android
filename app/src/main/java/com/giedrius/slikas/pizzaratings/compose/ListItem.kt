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
    Text(rating.address)
    Text("${rating.numberOfRatings} rating(s)")
    Text("${rating.averageRating} average rating")
  }
}

@Preview(showSystemUi = true)
@Composable
fun PreviewListItem() {
  ListItem(
    Rating(
      name = "Pizza & Nachos Pub",
      address = "Linkmenų g.4, Vilnius 09300",
      numberOfRatings = 1,
      averageRating = 3.00f
    )
  )
}