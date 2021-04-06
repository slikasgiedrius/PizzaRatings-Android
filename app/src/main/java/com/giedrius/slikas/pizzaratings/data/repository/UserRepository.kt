package com.giedrius.slikas.pizzaratings.data.repository

import com.giedrius.slikas.pizzaratings.data.service.UserService
import javax.inject.Inject

class UserRepository @Inject constructor(
  private val userService: UserService
) {
  suspend fun getUser() = userService.getUser()
}