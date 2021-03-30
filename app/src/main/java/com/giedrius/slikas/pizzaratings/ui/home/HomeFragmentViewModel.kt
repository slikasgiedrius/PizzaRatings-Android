package com.giedrius.slikas.pizzaratings.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.giedrius.slikas.pizzaratings.api.UserRepository
import com.giedrius.slikas.pizzaratings.api.User
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeFragmentViewModel @Inject constructor(
  private val userRepository: UserRepository
) : ViewModel() {

  private val _text = MutableLiveData<String>().apply {
    value = "This is home Fragment"
  }
  val text: LiveData<String> = _text

  val users = MutableLiveData<User>()

  fun fetchUsers() {
    viewModelScope.launch {
      userRepository.getUser().let {
        if (it.isSuccessful) {
          users.postValue(it.body())
        } else {
          println("Error ${it.body()}")
        }
      }
    }
  }
}