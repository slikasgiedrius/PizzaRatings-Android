package com.giedrius.slikas.pizzaratings.ui.favourites

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.giedrius.slikas.pizzaratings.data.repository.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class FavouritesFragmentViewModel @Inject constructor(
  val userRepository: UserRepository
) : ViewModel() {

  private val _text = MutableLiveData<String>().apply {
    value = "This is Favourites Fragment"
  }
  val text: LiveData<String> = _text
}