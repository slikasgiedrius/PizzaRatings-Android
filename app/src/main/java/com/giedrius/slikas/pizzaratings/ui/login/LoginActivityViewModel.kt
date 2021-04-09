package com.giedrius.slikas.pizzaratings.ui.login

import androidx.lifecycle.ViewModel
import com.giedrius.slikas.pizzaratings.data.repository.PizzaRepository
import com.google.firebase.auth.FirebaseAuth
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class LoginActivityViewModel @Inject constructor(
  val firebaseAuth: FirebaseAuth,
  val pizzaRepository: PizzaRepository
) : ViewModel() {

  fun saveUser() = pizzaRepository.saveUser()
}