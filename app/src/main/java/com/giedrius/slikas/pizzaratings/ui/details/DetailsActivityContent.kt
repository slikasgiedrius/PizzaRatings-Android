package com.giedrius.slikas.pizzaratings.ui.details

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.outlined.Favorite
import androidx.compose.material.icons.twotone.Favorite
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.giedrius.slikas.pizzaratings.compose.RatingSelector
import com.giedrius.slikas.pizzaratings.data.model.Rating
import com.giedrius.slikas.pizzaratings.data.model.UserData
import com.giedrius.slikas.pizzaratings.utils.mocks.getMockedRatings
import com.giedrius.slikas.pizzaratings.utils.mocks.getMockedUserData

@Composable
fun DetailsActivityContent(
  pizzeriaDetails: Rating?,
  onRatingClicked: (Int) -> Unit,
  userData: UserData,
  onMakeFavouriteClicked: () -> Unit,
  onRemoveFavouriteClicked: () -> Unit
) {
  if (pizzeriaDetails != null) {
    Column {
      Text("Pizzeria selected: ${pizzeriaDetails.name}")
      Text("Average rating: ${pizzeriaDetails.averageRating}")
      Text("Number of ratings: ${pizzeriaDetails.numberOfRatings}")
      Text("Addresses: ${pizzeriaDetails.addresses}")
      Text("Favourited by: ${pizzeriaDetails.favourited}")
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

      userData.let {
        val isFavourited = it.uid != null && pizzeriaDetails.favourited.contains(it.uid)

        if (isFavourited) {
          Icon(
            Icons.Filled.Favorite,
            contentDescription = "Favorite",
            modifier = Modifier.size(ButtonDefaults.IconSize)
          )

          Button(onClick = { onRemoveFavouriteClicked() }) {
            Text(text = "Remove favourite")
          }
        } else {
          Icon(
            Icons.TwoTone.Favorite,
            contentDescription = "Favorite",
            modifier = Modifier.size(ButtonDefaults.IconSize)
          )

          Button(onClick = { onMakeFavouriteClicked() }) {
            Text(text = "Make favourite")
          }
        }
      }
    }
  } else {
    Text("Can't fetch pizzerias data :(")
  }
}

@Preview(showSystemUi = true)
@Composable
fun PreviewDetailsActivityContent() {
  DetailsActivityContent(
    pizzeriaDetails = getMockedRatings().first(),
    onRatingClicked = {},
    userData = getMockedUserData(),
    onMakeFavouriteClicked = {},
    onRemoveFavouriteClicked = {}
  )
}