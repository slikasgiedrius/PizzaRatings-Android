package com.giedrius.slikas.pizzaratings.utils.extensions

fun Double.twoDecimalPoints(): Double {
  //avoid NaN's
  val value = String.format("%3f", this).toDouble()
  return if (value.isNaN()){
    0.0
  } else {
    value
  }
}