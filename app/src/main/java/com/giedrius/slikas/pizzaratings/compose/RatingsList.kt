package com.giedrius.slikas.pizzaratings.compose

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.giedrius.slikas.pizzaratings.data.model.Rating
import com.giedrius.slikas.pizzaratings.utils.mocks.getMockedRatings

@Composable
fun RatingsList(
  ratings: List<Rating>,
  onItemClicked: (String) -> Unit
) {
  LazyColumn {
    val averageRatingComparator = compareByDescending<Rating> { it.averageRating }
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
    ratings = getMockedRatings(),
    onItemClicked = { }
  )
}