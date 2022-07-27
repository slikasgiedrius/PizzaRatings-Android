package com.giedrius.slikas.pizzaratings.compose

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.annotation.ExperimentalCoilApi
import coil.compose.AsyncImage

@ExperimentalCoilApi
@Composable
fun PizzeriaLogo(logoUrl: String) {
  AsyncImage(
    model = logoUrl,
    contentDescription = "Pizzeria logo",
    contentScale = ContentScale.Crop,
    modifier = Modifier
      .size(80.dp)
      .padding(4.dp)
      .clip(CircleShape)
  )
}

@ExperimentalCoilApi
@Preview()
@Composable
fun PreviewPizzeriaLogo() {
  PizzeriaLogo("")
}