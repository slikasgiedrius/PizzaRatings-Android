package com.example.pizzaratings.api

import retrofit2.Response
import javax.inject.Inject

class ApiHelperImpl @Inject constructor(private val apiService: ApiService) : ApiHelper {

  override suspend fun getUser(): Response<User> = apiService.getUser()

}