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
  val pizzaRepository: PizzaRepository
) : ViewModel()