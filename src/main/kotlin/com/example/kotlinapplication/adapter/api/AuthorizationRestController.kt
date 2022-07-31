package com.example.kotlinapplication.adapter.api

import com.auth0.jwt.JWT
import com.auth0.jwt.algorithms.Algorithm
import com.example.kotlinapplication.application.UserService
import com.example.kotlinapplication.config.Environments
import com.example.kotlinapplication.domain.user.UserIssueToken
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api")
class AuthorizationRestController(
  private val userService: UserService,
  private val environments: Environments
) {

  @PostMapping("/refresh_token")
  fun refreshToken(@RequestBody userIssueToken: UserIssueToken): UserIssueToken {
    val jwt = JWT.decode(userIssueToken.accessToken)
    try {
      JWT.require(Algorithm.HMAC512(environments.accessTokenSecret))
    } catch (ex: Exception) {
      throw ex
    }
    return userService.issueToken(userId = jwt.subject)
  }
}