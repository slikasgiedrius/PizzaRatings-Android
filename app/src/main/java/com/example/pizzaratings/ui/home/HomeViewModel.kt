package com.example.pizzaratings.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pizzaratings.api.MainRepository
import com.example.pizzaratings.api.User
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
  private val mainRepository: MainRepository
) : ViewModel() {

  private val _text = MutableLiveData<String>().apply {
    value = "This is home Fragment"
  }
  val text: LiveData<String> = _text

  val users = MutableLiveData<User>()

  fun fetchUsers() {
    viewModelScope.launch {
      mainRepository.getUser().let {
        if (it.isSuccessful) {
          users.postValue(it.body())
        } else {
          println("Error ${it.body()}")
        }
      }
    }
  }
}