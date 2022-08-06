package com.example.kotlinapplication.config

import com.example.kotlinapplication.domain.exception.ApiApplicationException
import com.example.kotlinapplication.domain.exception.ErrorCode
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.InjectionPoint
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Scope

@Configuration
class LoggerConfig {

  @Bean
  @Scope("prototype")
  fun logger(ip: InjectionPoint): Logger =
    LoggerFactory.getLogger(
      ip.methodParameter?.containingClass ?: ip.field?.declaringClass
      ?: throw ApiApplicationException(
        message = "Loggerが取得できませんでした",
        errorCode = ErrorCode.SERVER_ERROR
      )
    )
}