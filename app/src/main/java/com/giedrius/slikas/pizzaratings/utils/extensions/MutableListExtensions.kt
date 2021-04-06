package com.giedrius.slikas.pizzaratings.utils.extensions

import com.giedrius.slikas.pizzaratings.data.model.Rating
import com.giedrius.slikas.pizzaratings.data.model.RatingResponse

@Suppress("UNCHECKED_CAST")
fun MutableList<RatingResponse>.toRating(): MutableList<Rating> {
  val pizzerias = mutableListOf<Rating>()

  for (item in this) {
    val numberOfRatings = item.ratings.size
    val sumOfRatings: Long = item.ratings.map { it.value }.sum()
    val averageRating: Double = sumOfRatings.toDouble() / numberOfRatings

    pizzerias.add(
      Rating(
        item.name,
        item.addresses,
        item.ratings as Map<String, Int>,
        numberOfRatings,
        averageRating
      )
    )
  }
  return pizzerias
}