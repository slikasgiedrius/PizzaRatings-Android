package com.giedrius.slikas.pizzaratings.data.repository

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.giedrius.slikas.pizzaratings.data.model.UserDetails
import com.giedrius.slikas.pizzaratings.data.service.UserService
import com.google.firebase.firestore.FirebaseFirestore
import javax.inject.Inject

class UserRepository @Inject constructor(
  private val userService: UserService,
  private val firestore: FirebaseFirestore
) {
  
  val onUsersDownloaded = MutableLiveData<List<UserDetails>>()
  
  suspend fun getUser() = userService.getUser()

  fun getUsers() {
    val users = mutableListOf<UserDetails>()

    firestore.collection("users")
      .get()
      .addOnSuccessListener { items ->
        for (item in items) {
          users.add(UserDetails(item.data["name"] as String, item.data["age"]))
          onUsersDownloaded.value = users
        }
      }
      .addOnFailureListener { exception ->
        Log.w("TAG", "Error getting documents: ", exception)
      }
  }

  fun saveUser() {
    val user = hashMapOf(
      "name" to "Darius",
      "age" to 25
    )

    firestore.collection("users")
      .document("darm")
      .set(user)
      .addOnSuccessListener {
        Log.v("Add", "Success")
      }
      .addOnFailureListener {
        Log.v("Add", "Failure")
      }
  }
}