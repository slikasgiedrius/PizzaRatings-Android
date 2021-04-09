package com.giedrius.slikas.pizzaratings.ui.home

import androidx.lifecycle.ViewModel
import com.giedrius.slikas.pizzaratings.data.repository.PizzaRepository
import com.giedrius.slikas.pizzaratings.utils.SingleLiveEvent
import com.giedrius.slikas.pizzaratings.utils.randomInt
import com.google.firebase.auth.FirebaseAuth
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeFragmentViewModel @Inject constructor(
  val pizzaRepository: PizzaRepository,
  firebaseAuth: FirebaseAuth
) : ViewModel() {

  val onUserNotFound = SingleLiveEvent<Unit>()

  private val user = firebaseAuth.currentUser

  fun saveRating(
    pizzeria: String,
    rating: Int
  ) {
    if (user != null) {
      pizzaRepository.saveRating(user.uid, pizzeria, rating)
    } else {
      onUserNotFound.call()
    }
  }
}