package com.example.pizzaratings.api

import retrofit2.Response
import retrofit2.http.GET

interface ApiService {
  @GET("todos/1")
  suspend fun getUser(): Response<User>
}