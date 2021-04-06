package com.giedrius.slikas.pizzaratings.compose

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.giedrius.slikas.pizzaratings.R

@Composable
fun NewsStory() {
  val typography = MaterialTheme.typography
  Column(
    modifier = Modifier
      .background(MaterialTheme.colors.surface)
      .padding(16.dp)
  ) {
    Image(
      painter = painterResource(id = R.drawable.header),
      contentDescription = "Accessibility is extremely important",
      modifier = Modifier
        .height(180.dp)
        .fillMaxWidth()
        .clip(shape = RoundedCornerShape(4.dp)),
      contentScale = ContentScale.Crop
    )
    Spacer(Modifier.height(16.dp))

    Text(
      text = "A day wandering through the sandhills in Shark Fin Cove, and a few of the " +
          "sights I saw",
      style = typography.h6,
      maxLines = 2,
      overflow = TextOverflow.Ellipsis
    )
    Text(text = "Davenport, California", style = typography.body2)
    Text(text = "December 2018", style = typography.body2)
  }
}

@Preview(showSystemUi = true)
@Composable
fun PreviewNewsStory() {
  NewsStory()
}