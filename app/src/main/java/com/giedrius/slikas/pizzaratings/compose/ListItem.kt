package com.giedrius.slikas.pizzaratings.compose

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.transform.CircleCropTransformation
import com.giedrius.slikas.pizzaratings.data.model.Rating
import com.google.accompanist.coil.CoilImage

@Composable
fun ListItem(
  rating: Rating
) {
  Row(
    verticalAlignment = Alignment.CenterVertically,
    modifier = Modifier.padding(16.dp)
  ) {
    CoilImage(
      data = rating.logoUrl,
      contentDescription = "Pizzeria logo",
      fadeIn = true,
      contentScale = ContentScale.Crop,
      modifier = Modifier
        .height(80.dp)
        .width(80.dp)
        .clip(shape = RoundedCornerShape(4.dp)),
    )
    Column(
      modifier = Modifier.padding(start = 8.dp, end = 8.dp)
    ) {
      Text(
        rating.name,
        style = MaterialTheme.typography.h6
      )
      Row {
        Text(
          text = "${rating.averageRating} average rating",
          style = MaterialTheme.typography.caption
        )
        Spacer(Modifier.width(2.dp))
        Text(
          text = "(${rating.numberOfRatings})",
          style = MaterialTheme.typography.caption
        )
      }
    }
  }
}

@Preview(showSystemUi = false)
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
      averageRating = 3.0,
      logoUrl = "https://drive.google.com/thumbnail?id=1jgJb_Ev4QeAbYTv-q2lnbXW7SieLAqNq"
    )
  )
}