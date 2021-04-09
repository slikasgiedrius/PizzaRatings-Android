package com.giedrius.slikas.pizzaratings.ui.details

import android.os.Bundle
import android.os.PersistableBundle
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.material.Text
import androidx.navigation.navArgs
import com.giedrius.slikas.pizzaratings.compose.base.PizzaRatingsTheme
import com.giedrius.slikas.pizzaratings.utils.extensions.toast
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailsActivity : AppCompatActivity() {

  private val viewModel: DetailsActivityViewModel by viewModels()
  private val args: DetailsActivityArgs by navArgs()

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContent {
      PizzaRatingsTheme {
        DetailsActivityContent(
          viewModel,
          ::onRatingSelected
        )
      }
    }

    handleObservers()
  }

  override fun onStart() {
    super.onStart()
    viewModel.loadPizzeriaDetails(args.name)
  }

  private fun handleObservers() {
    viewModel.onUserNotFound.observe(this) {
      userIdentificationProblem()
    }
  }

  private fun onRatingSelected(rating: Int) = viewModel.saveRating(args.name, rating)

  private fun userIdentificationProblem() = this.toast("User identification problem!")
}