package com.giedrius.slikas.pizzaratings.utils.extensions

import com.giedrius.slikas.pizzaratings.data.model.Rating
import com.giedrius.slikas.pizzaratings.data.model.RatingResponse

fun MutableList<RatingResponse>.toRating(): MutableList<Rating> {
  val pizzerias = mutableListOf<Rating>()

  for (item in this) {
    pizzerias.add(
      Rating(
        item.name,
        item.address,
        item.numberOfRatings.toInt(),
        item.averageRating.toDouble()
      )
    )
  }
  return pizzerias
}