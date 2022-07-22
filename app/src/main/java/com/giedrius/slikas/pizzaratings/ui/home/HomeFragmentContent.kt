package com.giedrius.slikas.pizzaratings.ui.home

import androidx.compose.foundation.layout.Column
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.tooling.preview.Preview
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.animateLottieCompositionAsState
import com.airbnb.lottie.compose.rememberLottieComposition
import com.giedrius.slikas.pizzaratings.R
import com.giedrius.slikas.pizzaratings.compose.RatingsList
import com.giedrius.slikas.pizzaratings.data.model.Rating
import com.giedrius.slikas.pizzaratings.data.repository.PizzaRepository
import com.giedrius.slikas.pizzaratings.utils.mocks.getMockedRatings

@Composable
fun HomeFragmentContent(
  pizzaData: List<Rating>?,
  onItemClicked: (String) -> Unit
) {

  Column {
    if (pizzaData != null) {
      RatingsList(
        pizzaData,
        onItemClicked,
      )
    } else {
      Column {
        val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.list_loader))
        val progress by animateLottieCompositionAsState(
          composition = composition,
          iterations = 5
        )

        LottieAnimation(
          composition,
          progress,
        )
      }
    }
  }
}

@Preview(showSystemUi = true)
@Composable
fun PreviewHomeFragmentContent() {
  HomeFragmentContent(
    pizzaData = getMockedRatings(),
    onItemClicked = { }
  )
}

@Preview(showSystemUi = true)
@Composable
fun PreviewHomeFragmentContentWithNullData() {
  HomeFragmentContent(
    pizzaData = null,
    onItemClicked = { }
  )
}