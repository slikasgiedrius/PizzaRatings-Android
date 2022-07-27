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
import com.giedrius.slikas.pizzaratings.data.model.Rating
import com.giedrius.slikas.pizzaratings.data.model.UserData

@Composable
fun DetailsActivityContent(
  pizzeriaDetails: Rating?,
  onRatingClicked: (Int) -> Unit,
  userData: UserData
) {
  if (pizzeriaDetails != null) {
    Column {
      Text("Pizzeria selected: ${pizzeriaDetails.name}")
      Text("Average rating: ${pizzeriaDetails.averageRating}")
      Text("Number of ratings: ${pizzeriaDetails.numberOfRatings}")
      Text("Addresses: ${pizzeriaDetails.addresses}")
      Text("Ratings: ${pizzeriaDetails.ratings}")
      Spacer(modifier = Modifier.height(4.dp))
      userData.let {
        Text("My user id: ${it.uid}")
        if (it.uid != null && pizzeriaDetails.ratings.containsKey(it.uid)) {
          val myRating = pizzeriaDetails.ratings.getValue(it.uid)
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