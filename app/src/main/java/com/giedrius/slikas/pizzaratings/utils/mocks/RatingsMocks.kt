package com.giedrius.slikas.pizzaratings.utils.mocks

import com.giedrius.slikas.pizzaratings.data.model.Rating

fun getMockedRatings(): List<Rating> = listOf(
  Rating(
    id = "12321456456456",
    name = "Pizza & Nachos Pub",
    addresses = listOf("Linkmenų g.4, Vilnius 09300"),
    ratings = mapOf(
      "gsli" to 3
    ),
    numberOfRatings = 1,
    averageRating = 3.0,
    logoUrl = "https://drive.google.com/thumbnail?id=1jgJb_Ev4QeAbYTv-q2lnbXW7SieLAqNq",
    favourited = emptyList()
  )
)