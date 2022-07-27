package com.giedrius.slikas.pizzaratings.ui.profile

import androidx.lifecycle.ViewModel
import com.giedrius.slikas.pizzaratings.data.model.UserData
import com.google.firebase.auth.FirebaseAuth
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ProfileFragmentViewModel @Inject constructor(
  val firebaseAuth: FirebaseAuth
) : ViewModel() {

  fun getCurrentUserData(): UserData {
    return UserData(
      uid = firebaseAuth.currentUser?.uid,
      name = firebaseAuth.currentUser?.displayName,
      email = firebaseAuth.currentUser?.email,
      phoneNumber = firebaseAuth.currentUser?.phoneNumber,
      photoUrl = firebaseAuth.currentUser?.photoUrl
    )
  }
}