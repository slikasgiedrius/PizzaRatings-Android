package com.giedrius.slikas.shared.utils.mocks

/*
Is being converted to Rating to eliminate firebase firestore limitations
*/
data class RatingResponse(
  val id: String?,
  val name: String?,
  val addresses: List<String>?,
  val ratings: Map<String, Long>?,
  val logoUrl: String?,
  val favourited: List<String>?
)
