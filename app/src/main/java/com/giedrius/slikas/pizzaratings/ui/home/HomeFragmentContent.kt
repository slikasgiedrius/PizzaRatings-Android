package com.giedrius.slikas.pizzaratings.ui.home

import androidx.compose.foundation.layout.Column
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import com.giedrius.slikas.pizzaratings.compose.RatingsList

@Composable
fun HomeFragmentContent(viewModel: HomeFragmentViewModel) {
  val pizzaData = viewModel.pizzaRepository.onPizzeriasDownloaded.observeAsState().value

  Column {
    if (pizzaData != null) {
      RatingsList(
        pizzaData,
        viewModel::onItemClicked,
      )
    } else {
      Text("There are no pizzerias :(")
    }
  }
}