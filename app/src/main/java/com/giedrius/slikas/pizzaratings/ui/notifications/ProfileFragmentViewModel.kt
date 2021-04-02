package com.giedrius.slikas.pizzaratings.ui.notifications

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ProfileFragmentViewModel @Inject constructor() : ViewModel() {

  private val _text = MutableLiveData<String>().apply {
    value = "This is Profile Fragment"
  }
  val text: LiveData<String> = _text
}