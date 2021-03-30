package com.giedrius.slikas.pizzaratings.api

import retrofit2.Response
import javax.inject.Inject

class UserServiceImpl @Inject constructor(private val apiService: ApiService) : UserService {
  override suspend fun getUser(): Response<User> = apiService.getUser()
}