package com.example.kotlinapplication.config

import com.example.kotlinapplication.application.UserService
import com.example.kotlinapplication.config.filter.AuthenticationFilter
import com.example.kotlinapplication.config.filter.AuthorizationFilter
import com.example.kotlinapplication.config.handler.ApiAccessDeniedHandler
import com.example.kotlinapplication.config.handler.ApiAuthenticationEntryPoint
import com.example.kotlinapplication.config.handler.ApiLogoutSuccessHandler
import com.example.kotlinapplication.domain.service.repository.RedisRepository
import org.slf4j.Logger
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.builders.WebSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.web.authentication.logout.HttpStatusReturningLogoutSuccessHandler
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler
import org.springframework.security.web.util.matcher.AntPathRequestMatcher
import org.springframework.web.cors.CorsConfiguration
import org.springframework.web.cors.CorsConfigurationSource
import org.springframework.web.cors.UrlBasedCorsConfigurationSource


@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true)
class SecurityConfig(
  private val userService: UserService,
  private val passwordConfig: PasswordConfig,
  private val redisRepository: RedisRepository,
  private val apiAuthenticationEntryPoint: ApiAuthenticationEntryPoint,
  private val apiAccessDeniedHandler: ApiAccessDeniedHandler,
  private val apiLogoutSuccessHandler: ApiLogoutSuccessHandler,
  private val log: Logger,
  private val environments: Environments
) :
  WebSecurityConfigurerAdapter() {

  override fun configure(web: WebSecurity) {
    web.ignoring().antMatchers("/images/**", "/js/**", "/css/**")
  }

  override fun configure(http: HttpSecurity) {
    http
      .csrf().disable() // Cookie/Sessionを利用しないため不要
      .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)

    http
      .authorizeRequests()
      .antMatchers("/api/login", "/api/refresh_token", "/api/signup").permitAll()
      .anyRequest().authenticated()

    http
      .exceptionHandling()
      .authenticationEntryPoint(apiAuthenticationEntryPoint)
      .accessDeniedHandler(apiAccessDeniedHandler)


    // ログイン用のフィルタ
    val authFilter =
      AuthenticationFilter(authenticationManager(), userService, redisRepository, log)
    authFilter.setRequiresAuthenticationRequestMatcher(
      AntPathRequestMatcher(
        "/api/login",
        "POST"
      )
    )
    authFilter.setAuthenticationManager(authenticationManagerBean())
    http.addFilter(authFilter).cors().configurationSource(this.corsConfigurationSource())

    // 認証用フィルタ
    val authorizationFilter =
      AuthorizationFilter(authenticationManager(), redisRepository, environments)
    http.addFilter(authorizationFilter)

    // ログアウト用の設定
    http
      .logout()
      .logoutRequestMatcher(
        AntPathRequestMatcher(
          "/api/logout",
          "POST"
        )
      )
      .invalidateHttpSession(true) //セッションの無効化
      .logoutSuccessHandler(apiLogoutSuccessHandler)
      .permitAll()

  }

  override fun configure(auth: AuthenticationManagerBuilder?) {
    auth?.userDetailsService(userService)?.passwordEncoder(passwordConfig.passwordEncoder())
  }

  fun logoutSuccessHandler(): LogoutSuccessHandler? {
    return HttpStatusReturningLogoutSuccessHandler()
  }

  private fun corsConfigurationSource(): CorsConfigurationSource {
    val config = CorsConfiguration()
    config.allowCredentials = true
    config.addAllowedMethod("GET")
    config.addAllowedMethod("POST")
    config.addAllowedMethod("PUT")
    config.addAllowedMethod("DELETE")
    config.addAllowedMethod("OPTIONS")
    config.addAllowedOrigin("http://localhost:3000")
    config.addAllowedHeader(CorsConfiguration.ALL)
    val source = UrlBasedCorsConfigurationSource()
    source.registerCorsConfiguration("/**", config)
    return source
  }

}