package com.giedrius.slikas.pizzaratings.utils.extensions

import com.giedrius.slikas.pizzaratings.data.model.Rating
import com.giedrius.slikas.pizzaratings.data.model.RatingResponse

@Suppress("UNCHECKED_CAST")
fun MutableList<RatingResponse>.toRating(): MutableList<Rating> {
  val pizzerias = mutableListOf<Rating>()

  for (item in this) {
    var numberOfRatings: Int = 0
    var sumOfRatings = 0L
    var averageRating = 0.0

    if (item.ratings != null){
      numberOfRatings = item.ratings.size
      sumOfRatings = item.ratings.map { it.value }.sum()
      averageRating = sumOfRatings.toDouble() / numberOfRatings
    }

    pizzerias.add(
      Rating(
        item.name,
        item.addresses,
        item.ratings as Map<String, Int>?,
        numberOfRatings,
        averageRating.twoDecimalPoints(),
        item.logoUrl
      )
    )
  }
  return pizzerias
}