package com.giedrius.slikas.pizzaratings.utils

import com.google.firebase.crashlytics.FirebaseCrashlytics

fun checkForIncompatibleTypes(ratings: Map<String, Long>): Map<String, Long> {
  val mutableRatings: MutableMap<String, Long> = ratings as MutableMap<String, Long>
  mutableRatings.forEach{
    if (it.value !is Long) {
      FirebaseCrashlytics.getInstance().recordException(Throwable("Key ${it.key} contains non Long type value"))
      ratings.remove(it.key)
    }
  }
  return mutableRatings.toMap()
}