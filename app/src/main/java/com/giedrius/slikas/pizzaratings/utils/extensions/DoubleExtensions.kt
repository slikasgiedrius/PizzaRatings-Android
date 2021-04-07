package com.giedrius.slikas.pizzaratings.utils.extensions

fun Double.twoDecimalPoints(): Double {
  return String.format("%.2f", this).toDouble()
}