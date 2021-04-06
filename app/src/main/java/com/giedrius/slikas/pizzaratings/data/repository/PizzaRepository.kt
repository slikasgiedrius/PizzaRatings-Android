package com.giedrius.slikas.pizzaratings.data.repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.giedrius.slikas.pizzaratings.data.model.Rating
import com.giedrius.slikas.pizzaratings.data.model.RatingResponse
import com.giedrius.slikas.pizzaratings.utils.extensions.toRating
import com.google.firebase.firestore.FirebaseFirestore
import javax.inject.Inject

class PizzaRepository @Inject constructor(
  private val firestore: FirebaseFirestore
) {
  private val _onPizzeriasDownloaded = MutableLiveData<List<Rating>>()
  val onPizzeriasDownloaded: LiveData<List<Rating>> = _onPizzeriasDownloaded

  @Suppress("UNCHECKED_CAST")
  fun getPizzerias() {
    val response = mutableListOf<RatingResponse>()

    firestore
      .collection(DB_VILNIUS)
      .get()
      .addOnSuccessListener { items ->
        for (item in items) {
          response.add(
            RatingResponse(
              item.data["name"] as String,
              item.data["addresses"] as List<String>,
              item.data["ratings"] as Map<String, Long>,
              item.data["logoUrl"] as String
            )
          )
        }
        _onPizzeriasDownloaded.value = response.toRating()
      }
      .addOnFailureListener { exception ->
        Log.e("Parsing error", "While executing 'getPizzerias': ", exception)
      }
  }

  fun saveRating(
    userId: String,
    pizzeria: String,
    rating: Int
  ) {
    //Save to Ratings DB
    firestore.collection(DB_RATINGS).document(pizzeria).update(
      mapOf(
        "$FIELD_RATINGS.$userId" to rating
      )
    )

    //Save to Vilnius DB
    firestore.collection(DB_VILNIUS).document(pizzeria).update(
      mapOf(
        "$FIELD_RATINGS.$userId" to rating
      )
    )
  }

  companion object {
    //DBs
    private const val DB_RATINGS = "Ratings"
    private const val DB_VILNIUS = "Vilnius"

    //Fields
    private const val FIELD_RATINGS = "ratings"
  }
}