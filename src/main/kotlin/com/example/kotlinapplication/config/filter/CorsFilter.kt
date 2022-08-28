package com.example.kotlinapplication.config.filter

import javax.servlet.FilterChain
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse
import org.springframework.web.filter.OncePerRequestFilter

// TODO SpringSecurity側のCorsFilterでいけそうな気もするがSpringSecurity使わない時にどうなるかわからないので残す
class CorsFilter : OncePerRequestFilter() {
  override fun doFilterInternal(
    request: HttpServletRequest,
    response: HttpServletResponse,
    filterChain: FilterChain
  ) {
    response.addHeader("Access-Control-Allow-Origin", "http://localhost:3000")
    response.addHeader(
      "Access-Control-Allow-Methods",
      "GET, POST, DELETE, PUT, PATCH, HEAD"
    )
    response.addHeader(
      "Access-Control-Allow-Headers",
      "*"
    )
    response.addHeader("Access-Control-Allow-Credentials", "true")
    response.addIntHeader("Access-Control-Max-Age", 10)
    filterChain.doFilter(request, response)
  }

}