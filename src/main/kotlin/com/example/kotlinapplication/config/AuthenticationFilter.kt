package com.example.kotlinapplication.config

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
import org.springframework.http.MediaType
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter

class AuthenticationFilter(
  authenticationManager: AuthenticationManager,
  private val userService: UserService
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
      println(message)
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
    val issueToken = userService.issueToken(authResult.name)
    val json = ObjectMapper().writeValueAsString(issueToken)
    response.contentType = MediaType.APPLICATION_JSON_VALUE
    response.writer.write(json)
    response.writer.flush()
  }
}