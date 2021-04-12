package com.giedrius.slikas.pizzaratings.compose

import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun RatingsCell(
  numberOfRatings: Int
) {
  val content = when (numberOfRatings) {
    0 -> "No ratings"
    1 -> "$numberOfRatings rating"
    else -> "$numberOfRatings ratings"
  }

  Text(
    text = content,
    style = MaterialTheme.typography.subtitle2,
    color = MaterialTheme.colors.onSurface
  )
}

@Preview(name = "No ratings")
@Composable
fun PreviewRatingsCellNoRatings(){
  RatingsCell(0)
}

@Preview(name = "1 Rating")
@Composable
fun PreviewRatingsCellOneRating(){
  RatingsCell(1)
}

@Preview(name = "2 Ratings")
@Composable
fun PreviewRatingsCellTwoRatings(){
  RatingsCell(2)
}