package com.example.kotlinapplication.config

import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Configuration

@Configuration
class Environments {
  @Value("\${jwt.accessToken.secret}")
  lateinit var accessTokenSecret: String

  @Value("\${jwt.accessToken.expired}")
  lateinit var accessTokenExpired: String
}