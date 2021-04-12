package com.giedrius.slikas.pizzaratings.ui.details

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.giedrius.slikas.pizzaratings.compose.RatingSelector

@Composable
fun DetailsActivityContent(
  viewModel: DetailsActivityViewModel,
  onRatingClicked: (Int) -> Unit
) {
  val pizzaData = viewModel.pizzaRepository.onPizzeriaDetailsDownloaded.observeAsState().value

  if (pizzaData != null) {
    Column {
      Text("Pizzeria selected: ${pizzaData.name}")
      Text("Average rating: ${pizzaData.averageRating}")
      Text("Number of ratings: ${pizzaData.numberOfRatings}")
      Text("Addresses: ${pizzaData.addresses}")
      Text("Ratings: ${pizzaData.ratings}")
      Spacer(modifier = Modifier.height(4.dp))
      viewModel.firebaseAuth.currentUser?.let {
        Text("My user id: ${it.uid}")
        if (pizzaData.ratings.containsKey(it.uid)) {
          val myRating = pizzaData.ratings.getValue(it.uid)
          Text("My rating is $myRating")
        }
      }
      Spacer(modifier = Modifier.height(4.dp))
      RatingSelector(
        onRatingClicked = onRatingClicked
      )
    }
  } else {
    Text("Can't fetch pizzerias data :(")
  }
}