package com.giedrius.slikas.pizzaratings.data.model

/*
Is being converted to Rating to eliminate firebase firestore limitations
*/
data class RatingResponse(
  val name: String,
  val address: String,
  val numberOfRatings: Long,
  val averageRating: Long
)
