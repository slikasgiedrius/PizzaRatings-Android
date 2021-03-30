package com.giedrius.slikas.pizzaratings.data.service

import com.giedrius.slikas.pizzaratings.data.model.User
import retrofit2.Response
import javax.inject.Inject

class UserServiceImpl @Inject constructor() : UserService {
  override suspend fun getUser(): Response<User> = getUser()
}