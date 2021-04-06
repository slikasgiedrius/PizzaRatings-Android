package com.giedrius.slikas.pizzaratings.data.repository

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.giedrius.slikas.pizzaratings.data.model.Overview
import com.giedrius.slikas.pizzaratings.data.model.Rating
import com.giedrius.slikas.pizzaratings.data.model.RatingResponse
import com.giedrius.slikas.pizzaratings.data.service.UserService
import com.giedrius.slikas.pizzaratings.utils.extensions.toRating
import com.google.firebase.firestore.FirebaseFirestore
import javax.inject.Inject

class UserRepository @Inject constructor(
  private val userService: UserService,
  private val firestore: FirebaseFirestore
) {
  
  val onPizzeriasDownloaded = MutableLiveData<List<Rating>>()
  val onOverviewDownloaded = MutableLiveData<List<Overview>>()
  
  suspend fun getUser() = userService.getUser()

  fun getPizzerias() {
    firestore
      .collection("Vilnius")
      .get()
      .addOnSuccessListener { items ->
        val response = mutableListOf<RatingResponse>()
        for (item in items) {
          response.add(
            RatingResponse(
              item.data["name"] as String,
              item.data["address"] as String,
              item.data["numberOfRatings"] as Long,
              item.data["averageRating"] as Long
            )
          )
        }
        onPizzeriasDownloaded.value = response.toRating()
      }
      .addOnFailureListener { exception ->
        Log.w("TAG", "Error getting documents: ", exception)
      }
  }

  fun getOverview() {
    val docRef = firestore
      .collection("Overview")
      .document("Dodo Pizza")
      .collection("locations")

    docRef
      .get()
      .addOnSuccessListener { items ->
        val response = mutableListOf<Overview>()

        for (item in items) {
          response.add(
            Overview(
              item.data["address"] as String,
              item.data["numberOfRatings"] as Long,
              item.data["averageRating"] as Long
            )
          )
        }
        onOverviewDownloaded.value = response
      }
      .addOnFailureListener { exception ->
        Log.w("TAG", "Error getting documents: ", exception)
      }
  }



//  fun saveUser() {
//    val user = hashMapOf(
//      "name" to "Darius",
//      "age" to 25
//    )
//
//    firestore.collection("users")
//      .document("darm")
//      .set(user)
//      .addOnSuccessListener {
//        Log.v("Add", "Success")
//      }
//      .addOnFailureListener {
//        Log.v("Add", "Failure")
//      }
//  }
}