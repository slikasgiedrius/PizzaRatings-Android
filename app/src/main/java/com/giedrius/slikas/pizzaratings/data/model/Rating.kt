package com.giedrius.slikas.pizzaratings.data.model

data class Rating(
  val name: String,
  val addresses: List<String>,
  val ratings: Map<String, Int>,
  val numberOfRatings: Int,
  val averageRating: Double,
  val logoUrl: String
)