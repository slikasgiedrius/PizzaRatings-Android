package com.giedrius.slikas.pizzaratings.compose

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.giedrius.slikas.pizzaratings.data.model.Rating

@Composable
fun RatingsList(
  ratings: List<Rating>
) {
  LazyColumn {
    items(ratings) { rating ->
      ListItem(rating)
    }
  }
}

@Preview(showSystemUi = true)
@Composable
fun PreviewRatingsList() {
  RatingsList(
    ratings = listOf(
      Rating(
        name = "Pizza & Nachos Pub",
        address = "Linkmenų g.4, Vilnius 09300",
        numberOfRatings = 1,
        averageRating = 3.00f
      ),
      Rating(
        name = "Pizza Verde",
        address = "Kalvarijų g. 101A, Vilnius 08219",
        numberOfRatings = 0,
        averageRating = 0
      )
    )
  )
}