package com.example.kotlinapplication.adapter.api

import com.example.kotlinapplication.application.UserService
import com.example.kotlinapplication.config.Environments
import com.example.kotlinapplication.domain.exception.ApiApplicationException
import com.example.kotlinapplication.domain.exception.ErrorCode
import com.example.kotlinapplication.domain.user.UserIssueToken
import com.example.kotlinapplication.util.JwtUtil
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api")
class TokenRestController(
  private val userService: UserService,
  private val environments: Environments
) {

  @PostMapping("/refresh_token")
  fun refreshToken(@RequestBody userIssueToken: UserIssueToken): UserIssueToken {
    try {
      val userId = JwtUtil.decodeToken(
        token = userIssueToken.accessToken,
        algorithmSecret = environments.accessTokenSecret
      )
      return userService.issueToken(userId = userId)
    } catch (ex: Exception) {
      throw ApiApplicationException(
        message = "リフレッシュトークンの生成に失敗しました",
        errorCode = ErrorCode.UN_AUTHORIZED
      )
    }
  }
}