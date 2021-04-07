package com.giedrius.slikas.pizzaratings.data.model

/*
Is being converted to Rating to eliminate firebase firestore limitations
*/
data class RatingResponse(
  val name: String?,
  val addresses: List<String>?,
  val ratings: Map<String, Long>?,
  val logoUrl: String?
)
