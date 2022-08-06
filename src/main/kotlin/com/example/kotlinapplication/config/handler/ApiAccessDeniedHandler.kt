package com.example.kotlinapplication.config.handler

import com.example.kotlinapplication.domain.error.ApiApplicationError
import com.example.kotlinapplication.domain.exception.ErrorCode
import com.fasterxml.jackson.databind.ObjectMapper
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.security.web.access.AccessDeniedHandler
import org.springframework.stereotype.Component

/**
 * ユーザーは認証済みだが未認可のリソースへアクセスしたときの処理
 *
 */
@Component("ApiAccessDeniedHandler")
class ApiAccessDeniedHandler : AccessDeniedHandler {
  override fun handle(
    request: HttpServletRequest,
    response: HttpServletResponse,
    accessDeniedException: org.springframework.security.access.AccessDeniedException
  ) {
    val errorResponse = ApiApplicationError(
      status = HttpStatus.UNAUTHORIZED,
      message = "権限がありません",
      errorCode = ErrorCode.UN_AUTHORIZED
    )
    val json = ObjectMapper().writeValueAsString(errorResponse)

    response.contentType = MediaType.APPLICATION_JSON_VALUE
    response.status = HttpStatus.UNAUTHORIZED.value()
    response.writer.write(json)
    response.writer.flush()
  }
}
