package com.giedrius.slikas.pizzaratings.utils.extensions

import com.giedrius.slikas.pizzaratings.data.model.Rating
import com.giedrius.slikas.pizzaratings.data.model.RatingResponse
import com.google.firebase.crashlytics.FirebaseCrashlytics

@Suppress("UNCHECKED_CAST")
fun MutableList<RatingResponse>.toRating(): MutableList<Rating> {
  val pizzerias = mutableListOf<Rating>()

  for (item in this) {
    var ratings = item.ratings ?: emptyMap()
    ratings = checkForIncompatibleTypes(ratings)

    val numberOfRatings = ratings.size
    val sumOfRatings = ratings.map { it.value }.sum()
    val averageRating = sumOfRatings.toDouble() / numberOfRatings

    pizzerias.add(
      Rating(
        item.name ?: "No name pizzeria",
        item.addresses ?: emptyList(),
        item.ratings as Map<String, Int>? ?: mapOf(),
        numberOfRatings,
        averageRating.roundTo(2),
        item.logoUrl ?: ""
      )
    )
  }
  return pizzerias
}

private fun checkForIncompatibleTypes(ratings: Map<String, Long>): Map<String, Long> {
  val mutableRatings: MutableMap<String, Long> = ratings as MutableMap<String, Long>
  mutableRatings.forEach{
    if (it.value !is Long) {
      FirebaseCrashlytics.getInstance().recordException(Throwable("Key ${it.key} contains non Long type value"))
      ratings.remove(it.key)
    }
  }
  return mutableRatings.toMap()
}