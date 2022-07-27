package com.giedrius.slikas.pizzaratings.data.model

import android.net.Uri

data class UserData(
  val uid: String?,
  val name: String?,
  val email: String?,
  val phoneNumber: String?,
  val photoUrl: Uri?
)