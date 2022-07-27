package com.giedrius.slikas.pizzaratings.ui.favourites

import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun FavouritesFragmentContent() {
  Column(
    horizontalAlignment = Alignment.CenterHorizontally,
    verticalArrangement = Arrangement.Center
  ) {
    Text(text = "To be implemented")
  }
}

@Preview(showSystemUi = true)
@Composable
fun PreviewFavouritesFragmentContent() {
  FavouritesFragmentContent()
}