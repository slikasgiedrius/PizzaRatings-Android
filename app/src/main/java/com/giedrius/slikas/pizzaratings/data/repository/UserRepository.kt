package com.giedrius.slikas.pizzaratings.data.repository

import com.giedrius.slikas.pizzaratings.data.model.UserData
import com.google.firebase.auth.FirebaseAuth
import javax.inject.Inject

class UserRepository @Inject constructor(
  private val firebaseAuth: FirebaseAuth
) {

  fun getCurrentUser() = firebaseAuth.currentUser

  fun getCurrentUserData(): UserData {
    return UserData(
      uid = firebaseAuth.currentUser?.uid,
      name = firebaseAuth.currentUser?.displayName,
      email = firebaseAuth.currentUser?.email,
      phoneNumber = firebaseAuth.currentUser?.phoneNumber,
      photoUrl = firebaseAuth.currentUser?.photoUrl
    )
  }

  fun signOut() = firebaseAuth.signOut()
}