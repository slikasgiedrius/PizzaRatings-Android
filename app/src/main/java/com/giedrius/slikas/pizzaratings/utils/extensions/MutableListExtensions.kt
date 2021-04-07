package com.giedrius.slikas.pizzaratings.utils.extensions

import com.giedrius.slikas.pizzaratings.data.model.Rating
import com.giedrius.slikas.pizzaratings.data.model.RatingResponse

@Suppress("UNCHECKED_CAST")
fun MutableList<RatingResponse>.toRating(): MutableList<Rating> {
  val pizzerias = mutableListOf<Rating>()

  for (item in this) {
    val ratings = item.ratings ?: emptyMap()
    val numberOfRatings = ratings.size
    val sumOfRatings = ratings.map { it.value }.sum()
    val averageRating = sumOfRatings.toDouble() / numberOfRatings

    pizzerias.add(
      Rating(
        item.name ?: "No name pizzeria",
        item.addresses ?: emptyList(),
        item.ratings as Map<String, Int>? ?: mapOf(),
        numberOfRatings,
        averageRating.twoDecimalPoints(),
        item.logoUrl ?: ""
      )
    )
  }
  return pizzerias
}