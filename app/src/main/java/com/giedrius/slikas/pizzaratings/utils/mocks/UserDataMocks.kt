package com.giedrius.slikas.pizzaratings.utils.mocks

import android.net.Uri
import com.giedrius.slikas.pizzaratings.data.model.UserData

fun getMockedUserData(): UserData {
  return UserData(
    uid = "7773",
    name = "Miglužė",
    email = "tst@gmail.com",
    phoneNumber = "+37063771063",
    photoUrl = Uri.parse("https://drive.google.com/thumbnail?id=1jgJb_Ev4QeAbYTv-q2lnbXW7SieLAqNq")
  )
}