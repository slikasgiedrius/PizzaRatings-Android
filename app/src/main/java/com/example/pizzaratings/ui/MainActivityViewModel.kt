package com.example.pizzaratings.ui

import androidx.lifecycle.ViewModel
import com.example.pizzaratings.utils.PizzaRatingsPreferences
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainActivityViewModel @Inject constructor(
  val preferences: PizzaRatingsPreferences
) : ViewModel()