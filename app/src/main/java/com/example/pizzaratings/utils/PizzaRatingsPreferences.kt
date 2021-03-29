package com.example.pizzaratings.utils

import android.content.Context
import android.content.SharedPreferences

class PizzaRatingsPreferences(context: Context) {
  private val sharedPref: SharedPreferences = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)

  private fun saveString(key: String, value: String) {
    val editor: SharedPreferences.Editor = sharedPref.edit()
    editor.putString(key, value)
    editor.apply()
  }

  private fun getString(key: String): String? {
    return sharedPref.getString(key, null)
  }

  fun clearSharedPreference() {
    val editor: SharedPreferences.Editor = sharedPref.edit()
    editor.clear()
    editor.apply()
  }

  companion object {
    private const val PREFS_NAME = "pizza_ratings"
  }
}