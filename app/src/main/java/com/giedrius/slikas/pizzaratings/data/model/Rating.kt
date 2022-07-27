package com.giedrius.slikas.pizzaratings.data.model

data class Rating(
  val id: String,
  val name: String,
  val addresses: List<String>,
  val ratings: Map<String, Int>,
  val numberOfRatings: Int,
  val averageRating: Double,
  val logoUrl: String,
  val favourited: List<String>
)