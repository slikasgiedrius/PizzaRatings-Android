package com.example.pizzaratings.api

import com.squareup.moshi.Json

data class User(
  @Json(name = "id")
  val id: Int = 0
)