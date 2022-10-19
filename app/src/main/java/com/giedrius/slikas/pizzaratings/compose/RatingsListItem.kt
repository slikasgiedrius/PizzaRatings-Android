package com.giedrius.slikas.pizzaratings.compose

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.giedrius.slikas.shared.data.Rating
import com.giedrius.slikas.shared.utils.mocks.getMockedRatings

@Composable
fun ListItem(
  rating: Rating,
  onItemClicked: (String) -> Unit
) {
  Card(
    modifier = Modifier
      .fillMaxSize()
      .padding(
        start = 16.dp,
        top = 8.dp,
        bottom = 8.dp,
        end = 16.dp
      )
      .clip(
        shape = RoundedCornerShape(8.dp)
      )
      .clickable(
        onClick = { onItemClicked(rating.name) }
      ),
  ) {
    Row(
      verticalAlignment = Alignment.CenterVertically,
    ) {
      PizzeriaLogo(rating.logoUrl)
      Column(
        modifier = Modifier.padding(start = 8.dp)
      ) {
        Text(
          rating.name,
          style = MaterialTheme.typography.h6,
          color = MaterialTheme.colors.onSurface
        )
        AverageRatingCell(rating.averageRating)
        Spacer(Modifier.width(2.dp))
        RatingsCell(rating.numberOfRatings)
      }
    }
  }
}

@Preview(showSystemUi = true)
@Composable
fun PreviewListItem() {
  ListItem(
    getMockedRatings().first(),
    onItemClicked = { }
  )
}