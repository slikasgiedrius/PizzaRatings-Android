package com.giedrius.slikas.pizzaratings.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.giedrius.slikas.pizzaratings.data.repository.UserRepository
import com.giedrius.slikas.pizzaratings.data.model.User
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeFragmentViewModel @Inject constructor(
  private val userRepository: UserRepository
) : ViewModel() {

  private val _user = MutableLiveData<User>()
  val user: LiveData<User> = _user

  fun fetchUsers() {
    viewModelScope.launch {
      userRepository.getUser().let {
        if (it.isSuccessful) {
          _user.postValue(it.body())
        } else {
          println("Error ${it.body()}")
        }
      }
    }
  }
}