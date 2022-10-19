package com.giedrius.slikas.shared.utils

import kotlin.random.Random

fun randomInt(from: Int = 1, to: Int = 5): Int {
  return Random.nextInt(from, to)
}

fun randomString(length: Int = 5) : String {
  val charset = ('a'..'z') + ('A'..'Z') + ('0'..'9')

  return List(length) { charset.random() }
    .joinToString("")
}