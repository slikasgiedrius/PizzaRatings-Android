package com.giedrius.slikas.pizzaratings.ui.details

import androidx.lifecycle.ViewModel
import com.giedrius.slikas.pizzaratings.data.repository.PizzaRepository
import com.giedrius.slikas.pizzaratings.data.repository.UserRepository
import com.giedrius.slikas.pizzaratings.utils.SingleLiveEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class DetailsActivityViewModel @Inject constructor(
  val pizzaRepository: PizzaRepository,
  val userRepository: UserRepository
) : ViewModel() {
  val onUserNotFound = SingleLiveEvent<Unit>()
  private val user = userRepository.getCurrentUserData()

  fun loadPizzeriaDetails(pizzeria: String) {
    pizzaRepository.getPizzeria(pizzeria)
  }

  fun saveRating(
    pizzeria: String,
    rating: Int
  ) {
    if (user.uid != null) {
      pizzaRepository.saveRating(user.uid, pizzeria, rating)
    } else {
      onUserNotFound.call()
    }
  }
}