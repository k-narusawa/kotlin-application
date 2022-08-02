package com.example.kotlinapplication.config

import com.example.kotlinapplication.domain.exception.ApiApplicationException
import com.example.kotlinapplication.domain.exception.ErrorCode
import com.example.kotlinapplication.domain.service.repository.RedisRepository
import com.example.kotlinapplication.util.JwtUtil
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse
import org.apache.commons.lang3.StringUtils
import org.springframework.http.HttpHeaders
import org.springframework.security.core.Authentication
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler
import org.springframework.stereotype.Component


/**
 * TODOログアウト成功時の処理
 *
 * @property redisRepository
 */
@Component("ApiLogoutSuccessHandler")
class ApiLogoutSuccessHandler(
  private val redisRepository: RedisRepository,
  private val environments: Environments
) : LogoutSuccessHandler {
  companion object {
    private const val TOKEN_PREFIX = "Bearer "
  }

  override fun onLogoutSuccess(
    request: HttpServletRequest?,
    response: HttpServletResponse?,
    authentication: Authentication?
  ) {

    val authHeader = request?.getHeader(HttpHeaders.AUTHORIZATION)
    val accessToken = StringUtils.substringAfter(authHeader, TOKEN_PREFIX)
    val userId = when {
      authHeader != null -> JwtUtil.decodeToken(
        token = accessToken,
        algorithmSecret = environments.accessTokenSecret
      )
      else -> throw ApiApplicationException(
        message = "error.unauthorized",
        errorCode = ErrorCode.UN_AUTHORIZED
      )
    }

    val accessTokenInRedis = redisRepository.findByKey(key = userId)
    if (accessToken != accessTokenInRedis)
      throw ApiApplicationException(
        message = "error.unauthorized",
        errorCode = ErrorCode.UN_AUTHORIZED
      )
    redisRepository.deleteByKey(key = userId)
  }
}