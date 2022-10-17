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

  fun saveRating(rating: Int) {
    if (user.uid != null) {
      pizzaRepository.onPizzeriaDetailsDownloaded.value?.let {
        pizzaRepository.saveRating(user.uid, it.name, rating)
      }
    } else {
      onUserNotFound.call()
    }
  }

  fun makeFavourite() {
    if (user.uid != null) {
      pizzaRepository.onPizzeriaDetailsDownloaded.value?.let {
        pizzaRepository.makeFavourite(
          userId = user.uid,
          pizzeriaName = it.name
        )
      }
    } else {
      onUserNotFound.call()
    }
  }

  fun removeFavourite() {
    if (user.uid != null) {
      pizzaRepository.onPizzeriaDetailsDownloaded.value?.let {
        pizzaRepository.removeFavourite(
          userId = user.uid,
          pizzeriaName = it.name
        )
      }
    } else {
      onUserNotFound.call()
    }
  }
}