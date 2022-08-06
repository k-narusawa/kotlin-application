package com.example.kotlinapplication.config.filter

import com.example.kotlinapplication.domain.exception.ApiApplicationException
import com.example.kotlinapplication.domain.exception.ErrorCode
import com.example.kotlinapplication.util.DateTimeUtil
import java.time.temporal.ChronoUnit
import javax.servlet.FilterChain
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse
import org.slf4j.Logger
import org.springframework.stereotype.Component
import org.springframework.web.filter.OncePerRequestFilter

@Component("RequestFilter")
class RequestFilter(private val log: Logger) : OncePerRequestFilter() {
  override fun doFilterInternal(
    request: HttpServletRequest,
    response: HttpServletResponse,
    chain: FilterChain
  ) {
    try {
      val start = DateTimeUtil.now()
      val startMessage = String.format(
        "Request START %s %s",
        request.method, request.requestURI
      )
      log.info(startMessage)
      chain.doFilter(request, response)
      val requestTime = ChronoUnit.MILLIS.between(start, DateTimeUtil.now())
      val endMessage = String.format(
        "Request END RequestTime: %sms",
        requestTime
      )
      log.info(endMessage)
    } catch (ex: Exception) {
      throw ApiApplicationException(message = "フィルタ内エラー", errorCode = ErrorCode.SERVER_ERROR)
    }
  }
}