package com.giedrius.slikas.pizzaratings.ui.home

import androidx.compose.foundation.layout.Column
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import com.giedrius.slikas.pizzaratings.compose.RatingsList

@Composable
fun HomeFragmentContent(
  viewModel: HomeFragmentViewModel,
  onItemClicked: (String) -> Unit
) {
  val pizzaData = viewModel.pizzaRepository.onPizzeriasListDownloaded.observeAsState().value

  Column {
    if (pizzaData != null) {
      RatingsList(
        pizzaData,
        onItemClicked,
      )
    } else {
      Text("There are no pizzerias :(")
    }
  }
}