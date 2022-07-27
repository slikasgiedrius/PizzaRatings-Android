package com.giedrius.slikas.pizzaratings.utils.extensions

import com.giedrius.slikas.pizzaratings.data.model.Rating
import com.giedrius.slikas.pizzaratings.data.model.RatingResponse
import com.giedrius.slikas.pizzaratings.utils.checkForIncompatibleTypes
import com.google.firebase.crashlytics.FirebaseCrashlytics

@Suppress("UNCHECKED_CAST")
fun MutableList<RatingResponse>.toRating(): MutableList<Rating> {
  val pizzerias = mutableListOf<Rating>()

  for (item in this) {
    var ratings = item.ratings ?: emptyMap()
    if (ratings.isNotEmpty()) {
      ratings = checkForIncompatibleTypes(ratings)
    }

    val numberOfRatings = ratings.size
    val sumOfRatings = ratings.map { it.value }.sum()
    val averageRating = sumOfRatings.toDouble() / numberOfRatings

    pizzerias.add(
      Rating(
        id = item.id ?: "",
        name = item.name ?: "No name pizzeria",
        addresses = item.addresses ?: emptyList(),
        ratings = item.ratings as Map<String, Int>? ?: mapOf(),
        numberOfRatings = numberOfRatings,
        averageRating = averageRating.roundTo(2),
        logoUrl = item.logoUrl ?: ""
      )
    )
  }
  return pizzerias
}