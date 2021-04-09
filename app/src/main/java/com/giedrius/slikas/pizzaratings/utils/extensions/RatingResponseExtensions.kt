package com.giedrius.slikas.pizzaratings.utils.extensions

import com.giedrius.slikas.pizzaratings.data.model.Rating
import com.giedrius.slikas.pizzaratings.data.model.RatingResponse
import com.giedrius.slikas.pizzaratings.utils.checkForIncompatibleTypes

@Suppress("UNCHECKED_CAST")
fun RatingResponse.toRating(): Rating {
  var ratings = this.ratings ?: emptyMap()
  ratings = checkForIncompatibleTypes(ratings)

  val numberOfRatings = ratings.size
  val sumOfRatings = ratings.map { it.value }.sum()
  val averageRating = sumOfRatings.toDouble() / numberOfRatings

  return Rating(
    name = this.name ?: "",
    addresses = this.addresses ?: emptyList(),
    ratings = this.ratings as Map<String, Int>? ?: mapOf(),
    numberOfRatings,
    averageRating.roundTo(2),
    this.logoUrl ?: ""
  )
}