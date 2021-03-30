package com.giedrius.slikas.pizzaratings.api

import javax.inject.Inject

class UserRepository @Inject constructor(private val userService: UserService) {
  suspend fun getUser() = userService.getUser()
}