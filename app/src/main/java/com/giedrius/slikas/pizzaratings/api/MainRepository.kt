package com.giedrius.slikas.pizzaratings.api

import javax.inject.Inject

class MainRepository @Inject constructor(private val apiHelper: ApiHelper) {

  suspend fun getUser() =  apiHelper.getUser()

}