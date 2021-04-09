package com.giedrius.slikas.pizzaratings.ui.details

import androidx.compose.foundation.layout.Column
import androidx.compose.material.Text
import androidx.compose.runtime.Composable

@Composable
fun DetailsFragmentContent(
  pizzeriaName: String,
  viewModel: DetailsFragmentViewModel
) {
  Column {
    Text("Details fragment")
    Text("Pizzeria selected: $pizzeriaName")
  }
}