package com.giedrius.slikas.pizzaratings.api

import retrofit2.Response
import retrofit2.http.GET

interface UserService {
  @GET("todos/1")
  suspend fun getUser(): Response<User>
}