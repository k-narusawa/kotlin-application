package com.example.kotlinapplication.config

import com.example.kotlinapplication.domain.error.ApiApplicationError
import com.example.kotlinapplication.domain.exception.ErrorCode
import com.fasterxml.jackson.databind.ObjectMapper
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.security.core.AuthenticationException
import org.springframework.security.web.AuthenticationEntryPoint
import org.springframework.stereotype.Component


/**
 * 未認証のユーザーが認証の必要なAPIにアクセスしたときの処理
 *
 */
@Component("ApiAuthenticationEntryPoint")
class ApiAuthenticationEntryPoint : AuthenticationEntryPoint {
  override fun commence(
    request: HttpServletRequest?,
    response: HttpServletResponse,
    exception: AuthenticationException
  ) {
    val errorResponse = ApiApplicationError(
      status = HttpStatus.UNAUTHORIZED,
      message = "error.unauthorized",
      errorCode = ErrorCode.UN_AUTHORIZED
    )
    val json = ObjectMapper().writeValueAsString(errorResponse)

    response.contentType = MediaType.APPLICATION_JSON_VALUE
    response.status = HttpStatus.UNAUTHORIZED.value()
    response.writer.write(json)
    response.writer.flush()
  }
}