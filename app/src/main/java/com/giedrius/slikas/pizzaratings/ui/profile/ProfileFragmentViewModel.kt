package com.giedrius.slikas.pizzaratings.ui.profile

import androidx.lifecycle.ViewModel
import com.giedrius.slikas.pizzaratings.data.repository.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ProfileFragmentViewModel @Inject constructor(
  private val userRepository: UserRepository
) : ViewModel() {

  fun getCurrentUserData() = userRepository.getCurrentUserData()

  fun signOut() = userRepository.signOut()
}