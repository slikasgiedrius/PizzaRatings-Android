package com.giedrius.slikas.pizzaratings.api.repository

import com.giedrius.slikas.pizzaratings.api.service.UserService
import javax.inject.Inject

class UserRepository @Inject constructor(private val userService: UserService) {
  suspend fun getUser() = userService.getUser()
}