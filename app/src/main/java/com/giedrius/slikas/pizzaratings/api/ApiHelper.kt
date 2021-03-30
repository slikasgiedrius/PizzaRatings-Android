package com.giedrius.slikas.pizzaratings.api

import retrofit2.Response

interface ApiHelper {
  suspend fun getUser(): Response<User>
}