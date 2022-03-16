package com.giedrius.slikas.pizzaratings.ui.home

import androidx.compose.foundation.layout.Column
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.animateLottieCompositionAsState
import com.airbnb.lottie.compose.rememberLottieComposition
import com.giedrius.slikas.pizzaratings.R
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