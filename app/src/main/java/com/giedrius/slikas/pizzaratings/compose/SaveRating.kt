package com.giedrius.slikas.pizzaratings.compose

import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import kotlin.random.Random

@Composable
fun SaveRating(
  onClick: (String, String, Int) -> Unit
) {
  Button(
    onClick = {
      onClick(
        getRandomString(),
        "Pizza & Nachos Pub",
        randomInt()
      )
    }
  ) {
    Text("Save rating")
  }
}

fun getRandomString(length: Int = 5) : String {
  val charset = ('a'..'z') + ('A'..'Z') + ('0'..'9')

  return List(length) { charset.random() }
    .joinToString("")
}

fun randomInt(): Int {
  return Random.nextInt(from = 1, until = 5)
}