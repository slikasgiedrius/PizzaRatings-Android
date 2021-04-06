package com.giedrius.slikas.pizzaratings.data.model

data class Rating(
  val name: String,
  val address: String,
  val numberOfRatings: Int,
  val averageRating: Double
)