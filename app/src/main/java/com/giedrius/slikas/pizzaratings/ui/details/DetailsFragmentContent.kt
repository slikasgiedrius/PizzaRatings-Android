package com.giedrius.slikas.pizzaratings.ui.details

import androidx.compose.foundation.layout.Column
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import com.giedrius.slikas.pizzaratings.data.model.Rating
import com.giedrius.slikas.pizzaratings.data.repository.PizzaRepository

@Composable
fun DetailsFragmentContent(
  viewModel: DetailsFragmentViewModel
) {
  val pizzaData = viewModel.pizzaRepository.onPizzeriaDetailsDownloaded.observeAsState().value

  if (pizzaData != null) {
    Column {
      Text("Pizzeria selected: ${pizzaData.name}")
      Text("Average rating: ${pizzaData.averageRating}")
      Text("Number of ratings: ${pizzaData.numberOfRatings}")
      Text("Addresses: ${pizzaData.addresses}")
    }
  } else {
    Text("Can't fetch pizzerias data :(")
  }
}