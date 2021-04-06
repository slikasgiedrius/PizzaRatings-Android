package com.giedrius.slikas.pizzaratings.ui.favourites

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.layout.Column
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.giedrius.slikas.pizzaratings.compose.RatingsList
import com.giedrius.slikas.pizzaratings.compose.base.PizzaRatingsTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FavouritesFragment : Fragment() {

  private lateinit var viewModel: FavouritesFragmentViewModel

  override fun onCreateView(
    inflater: LayoutInflater,
    container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View {
    return ComposeView(requireContext()).apply {
      setContent {
        PizzaRatingsTheme {
          FavouritesFragmentContent(viewModel)
        }
      }
    }
  }

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)
    viewModel = ViewModelProvider(requireActivity()).get(FavouritesFragmentViewModel::class.java)
  }
}

@Composable
fun FavouritesFragmentContent(viewModel: FavouritesFragmentViewModel) {
  val pizzaData = viewModel.userRepository.onPizzeriasDownloaded.observeAsState().value

  Column {
    if (pizzaData != null) {
      RatingsList(ratings = pizzaData)
    }
    Button(
      onClick = { viewModel.userRepository.getPizzerias() }
    ) {
      Text("Firebase DB call for pizza")
    }

    Button(
      onClick = { viewModel.userRepository.saveRating(
        pizzeria = "Pizza & Nachos Pub",
        userId = "darm",
        rating = 4
      ) }
    ) {
      Text("Save rating")
    }
  }
}