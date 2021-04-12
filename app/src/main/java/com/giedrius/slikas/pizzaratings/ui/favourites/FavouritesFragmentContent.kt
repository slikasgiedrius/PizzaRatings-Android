package com.giedrius.slikas.pizzaratings.ui.favourites

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun FavouritesFragmentContent(
  viewModel: FavouritesFragmentViewModel
) {

  Card(Modifier.padding(8.dp),elevation = 8.dp){
    Text("This is a Card")
  }
}