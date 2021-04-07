package com.giedrius.slikas.pizzaratings.compose

import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun AverageRatingCell(
  averageRating: Double
) {
  when (averageRating) {
    0.0 -> {
      //Do nothing (remove the textView)
    }
    else -> {
      Text(
        text = "$averageRating average rating",
        style = MaterialTheme.typography.subtitle1
      )
    }
  }
}

@Preview(name = "Rating cell")
@Composable
fun PreviewAverageRatingCell() {
  AverageRatingCell(2.0)
}

@Preview(name = "Rating cell with 0.0")
@Composable
fun PreviewAverageRatingCellWithZero() {
  AverageRatingCell(0.0)
}