package com.giedrius.slikas.pizzaratings.compose

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.giedrius.slikas.pizzaratings.data.model.Rating

@Composable
fun RatingsList(
  ratings: List<Rating>,
  onItemClicked: (String) -> Unit
) {
  LazyColumn {
    val averageRatingComparator = compareByDescending<Rating>{ it.averageRating }
    val fullComparator = averageRatingComparator.thenByDescending { it.numberOfRatings }
    val sortedList = ratings.sortedWith(fullComparator)

    items(sortedList) { rating ->
      ListItem(
        rating,
        onItemClicked
      )
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
        addresses = listOf("Linkmenų g.4, Vilnius 09300"),
        ratings = mapOf(
          "gsli" to 3
        ),
        numberOfRatings = 1,
        averageRating = 3.0,
        logoUrl = "https://drive.google.com/thumbnail?id=1jgJb_Ev4QeAbYTv-q2lnbXW7SieLAqNq"
      )
    ),
    onItemClicked = { }
  )
}