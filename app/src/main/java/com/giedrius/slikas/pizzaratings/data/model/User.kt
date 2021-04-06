package com.giedrius.slikas.pizzaratings.data.model

import com.squareup.moshi.Json

data class User(
  @Json(name = "id")
  val id: Int? = null
)