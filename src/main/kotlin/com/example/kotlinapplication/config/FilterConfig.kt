package com.example.kotlinapplication.config

import com.example.kotlinapplication.config.filter.RequestFilter
import org.slf4j.Logger
import org.springframework.boot.web.servlet.FilterRegistrationBean
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class FilterConfig(private val log: Logger) {

  @Bean
  fun requestFilter() =
    FilterRegistrationBean(RequestFilter(log))
      .run {
        addUrlPatterns("/*")
        order = Integer.MIN_VALUE
      }

}