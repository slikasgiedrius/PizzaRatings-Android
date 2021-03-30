package com.giedrius.slikas.pizzaratings.api.service

import com.giedrius.slikas.pizzaratings.api.model.User
import retrofit2.Response
import javax.inject.Inject

class UserServiceImpl @Inject constructor() : UserService {
  override suspend fun getUser(): Response<User> = getUser()
}