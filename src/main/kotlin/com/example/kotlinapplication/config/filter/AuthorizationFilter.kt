package com.example.kotlinapplication.config.filter

import com.example.kotlinapplication.config.Environments
import com.example.kotlinapplication.domain.service.repository.RedisRepository
import com.example.kotlinapplication.util.JwtUtil
import javax.servlet.FilterChain
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse
import org.apache.commons.lang3.StringUtils
import org.springframework.http.HttpHeaders
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter


class AuthorizationFilter(
  authenticationManager: AuthenticationManager,
  private val redisRepository: RedisRepository,
  private val environments: Environments
) :
  BasicAuthenticationFilter(authenticationManager) {
  companion object {
    private const val TOKEN_PREFIX = "Bearer "
  }

  override fun doFilterInternal(
    request: HttpServletRequest,
    response: HttpServletResponse,
    chain: FilterChain
  ) {
    val authorizationHeader = request.getHeader(HttpHeaders.AUTHORIZATION)

    // 認証失敗時
    if (authorizationHeader == null || !authorizationHeader.startsWith(TOKEN_PREFIX)) {
      chain.doFilter(request, response)
      return
    }

    val authentication = getAuthentication(request = request)
    SecurityContextHolder.getContext().authentication = authentication

    // 後続のフィルタへ
    chain.doFilter(request, response)
  }

  private fun getAuthentication(request: HttpServletRequest): UsernamePasswordAuthenticationToken? {
    val authHeader = request.getHeader(HttpHeaders.AUTHORIZATION)
    val token = StringUtils.substringAfter(authHeader, TOKEN_PREFIX)

    if (token != null) {
      val userId =
        JwtUtil.decodeToken(token = token, algorithmSecret = environments.accessTokenSecret)
      val accessTokenInRedis = redisRepository.findByKey(key = userId)

      if (accessTokenInRedis == token) // redisに保存されている値とアクセストークンの一致を確認
        return UsernamePasswordAuthenticationToken(userId, null, ArrayList())
    }
    return null
  }
}