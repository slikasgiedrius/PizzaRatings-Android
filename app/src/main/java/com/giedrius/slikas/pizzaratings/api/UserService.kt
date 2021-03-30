package com.giedrius.slikas.pizzaratings.api

import retrofit2.Response

interface UserService {
  suspend fun getUser(): Response<User>
}