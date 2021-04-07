package com.giedrius.slikas.pizzaratings.ui.favourites.compose

import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.google.accompanist.coil.CoilImage

@Composable
fun PizzeriaLogo(logoUrl: String) {
  CoilImage(
    data = logoUrl,
    contentDescription = "Pizzeria logo",
    fadeIn = true,
    contentScale = ContentScale.Crop,
    modifier = Modifier.size(80.dp)
  )
}

@Preview()
@Composable
fun PreviewPizzeriaLogo() {
  PizzeriaLogo("")
}