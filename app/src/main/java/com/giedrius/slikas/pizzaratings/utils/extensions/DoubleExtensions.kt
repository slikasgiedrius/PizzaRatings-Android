package com.giedrius.slikas.pizzaratings.utils.extensions

import kotlin.math.pow
import kotlin.math.roundToInt

fun Double.roundTo(numFractionDigits: Int): Double {
  val value = if (this.isNaN()) 0.0 else this
  val factor = 10.0.pow(numFractionDigits.toDouble())
  return (value * factor).roundToInt() / factor
}
