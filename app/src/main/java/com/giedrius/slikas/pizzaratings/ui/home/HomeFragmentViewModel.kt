package com.giedrius.slikas.pizzaratings.ui.home

import androidx.lifecycle.ViewModel
import com.giedrius.slikas.pizzaratings.data.repository.PizzaRepository
import com.giedrius.slikas.pizzaratings.utils.randomInt
import com.giedrius.slikas.pizzaratings.utils.randomString
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeFragmentViewModel @Inject constructor(
  val pizzaRepository: PizzaRepository
) : ViewModel() {

  fun saveRating(
    userId: String,
    pizzeria: String,
    rating: Int
  ) = pizzaRepository.saveRating(userId, pizzeria, rating)

  fun onItemClicked(pizzeriaName: String){
    saveRating(
      randomString(),
      pizzeriaName,
      randomInt()
    )
  }
}