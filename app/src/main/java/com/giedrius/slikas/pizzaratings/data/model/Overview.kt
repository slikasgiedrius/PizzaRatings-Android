package com.giedrius.slikas.pizzaratings.data.model

data class Overview(
  val address: String,
  val numberOfRatings: Long,
  val averageRating: Long
)