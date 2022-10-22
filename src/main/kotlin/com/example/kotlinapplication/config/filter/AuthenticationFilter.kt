package com.example.kotlinapplication.config.filter

import com.example.kotlinapplication.adapter.infrastructure.repository.RedisRepository
import com.example.kotlinapplication.application.UserService
import com.example.kotlinapplication.domain.exception.ApiApplicationException
import com.example.kotlinapplication.domain.exception.ErrorCode
import com.example.kotlinapplication.domain.user.UserAuthenticationForm
import com.fasterxml.jackson.databind.ObjectMapper
import java.io.IOException
import java.util.*
import javax.servlet.FilterChain
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse
import org.slf4j.Logger
import org.springframework.http.MediaType
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter

class AuthenticationFilter(
  authenticationManager: AuthenticationManager,
  private val userService: UserService,
  private val redisRepository: RedisRepository,
  private val log: Logger
) :
  UsernamePasswordAuthenticationFilter(authenticationManager) {

  override fun attemptAuthentication(
    request: HttpServletRequest,
    response: HttpServletResponse
  ): Authentication {
    try {
      val message = String.format(
        "[%s] %-4s %s",
        request.localAddr, request.method, request.requestURI
      )
      log.debug(message)
      val userAuthenticationForm: UserAuthenticationForm =
        ObjectMapper().readValue(request.inputStream, UserAuthenticationForm::class.java)

      return authenticationManager.authenticate(
        UsernamePasswordAuthenticationToken(
          /* principal   */ userAuthenticationForm.loginId,
          /* credentials */ userAuthenticationForm.password,
          /* authorities */ Collections.emptyList()
        )
      )
    } catch (ex: IOException) {
      throw ApiApplicationException(message = "フィルタ内エラー", errorCode = ErrorCode.SERVER_ERROR)
    }
  }

  override fun successfulAuthentication(
    request: HttpServletRequest,
    response: HttpServletResponse,
    chain: FilterChain,
    authResult: Authentication
  ) {
    val issueToken = userService.issueToken(userId = authResult.name)

    // userIdをkeyにしてアクセストークンを保存
    redisRepository.save(key = authResult.name, value = issueToken.accessToken)
    val json = ObjectMapper().writeValueAsString(issueToken)
    response.contentType = MediaType.APPLICATION_JSON_VALUE
    response.writer.write(json)
    response.writer.flush()
  }
}