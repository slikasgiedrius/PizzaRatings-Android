package com.giedrius.slikas.pizzaratings.data.repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.giedrius.slikas.pizzaratings.data.model.Rating
import com.giedrius.slikas.pizzaratings.data.model.RatingResponse
import com.giedrius.slikas.pizzaratings.data.model.UserData
import com.giedrius.slikas.pizzaratings.utils.extensions.toRating
import com.google.firebase.firestore.FieldValue
import com.google.firebase.firestore.FirebaseFirestore
import javax.inject.Inject

class PizzaRepository @Inject constructor(
  private val firestore: FirebaseFirestore
) {
  //List
  private val _onPizzeriasListDownloaded = MutableLiveData<List<Rating>>()
  val onPizzeriasListDownloaded: LiveData<List<Rating>> = _onPizzeriasListDownloaded

  //Details
  private val _onPizzeriaDetailsDownloaded = MutableLiveData<Rating>()
  val onPizzeriaDetailsDownloaded: LiveData<Rating> = _onPizzeriaDetailsDownloaded

  @Suppress("UNCHECKED_CAST")
  fun getPizzeriasList() {
    val response = mutableListOf<RatingResponse>()

    firestore
      .collection(DB_RESTAURANTS)
      .addSnapshotListener { value, e ->
        if (e != null) {
          Log.w("Parsing error", "While executing 'getPizzeriasList' : ", e)
          return@addSnapshotListener
        }

        //Assuming it's not null because it passed the above check
        for (item in value!!) {
          response.add(
            RatingResponse(
              id = item["id"] as String?,
              name = item.data["name"] as String?,
              addresses = item.data["addresses"] as List<String>?,
              ratings = item.data["ratings"] as Map<String, Long>?,
              logoUrl = item.data["logoUrl"] as String?,
              favourited = item.data["favourited"] as List<String>?
            )
          )
        }
        _onPizzeriasListDownloaded.value = response.toRating()
        response.clear()
      }
  }

  @Suppress("UNCHECKED_CAST")
  fun getPizzeria(pizzeria: String) {

    firestore
      .collection(DB_RESTAURANTS)
      .document(pizzeria)
      .addSnapshotListener { value, e ->
        if (e != null) {
          Log.w("Parsing error", "While executing 'getPizzeria' : ", e)
          return@addSnapshotListener
        }

        if (value != null) {
          val response = RatingResponse(
            id = value.data?.get("id") as String?,
            name = value.data?.get("name") as String?,
            addresses = value.data?.get("addresses") as List<String>?,
            ratings = value.data?.get("ratings") as Map<String, Long>?,
            logoUrl = value.data?.get("logoUrl") as String?,
            favourited = value.data?.get("favourited") as List<String>?
          )
          _onPizzeriaDetailsDownloaded.value = response.toRating()
        }

      }
  }

  fun saveRating(
    userId: String,
    pizzeria: String,
    rating: Int
  ) {
    //Save
    firestore.collection(DB_RESTAURANTS).document(pizzeria).update(
      mapOf(
        "$FIELD_RATINGS.$userId" to rating
      )
    )

    //Save to Users DB
    firestore.collection(DB_USERS).document(userId).update(
      mapOf(
        "$FIELD_RATINGS.$pizzeria" to rating
      )
    )
  }

  fun makeFavourite(
    userId: String,
    pizzeriaName: String
  ) {
    //Save users id under Pizzeria profile
    firestore.collection(DB_RESTAURANTS).document(pizzeriaName).update(FIELD_FAVOURITED, FieldValue.arrayUnion(userId))

    //Save favourite pizzeria name under Users DB
    firestore.collection(DB_USERS).document(userId).update(FIELD_FAVOURITES, FieldValue.arrayUnion(pizzeriaName))
  }

  fun saveUser(userData: UserData) {
    if (userData.uid != null) {
      val data = hashMapOf(
        FIELD_USER_UID to userData.uid,
        FIELD_USER_NAME to userData.name,
        FIELD_EMAIL to userData.email,
      )
      firestore.collection(DB_USERS).document(userData.uid).set(data)
    }
  }

  companion object {
    //DBs
    private const val DB_VILNIUS = "Vilnius"
    private const val DB_RESTAURANTS = "Restaurants"
    private const val DB_USERS = "Users"

    //Fields
    private const val FIELD_RATINGS = "ratings"
    private const val FIELD_USER_UID = "id"
    private const val FIELD_USER_NAME = "name"
    private const val FIELD_EMAIL = "email"
    private const val FIELD_FAVOURITES = "favourites"
    private const val FIELD_FAVOURITED = "favourited"
  }
}