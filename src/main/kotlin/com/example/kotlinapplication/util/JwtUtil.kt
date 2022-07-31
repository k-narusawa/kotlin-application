package com.example.kotlinapplication.util

import com.auth0.jwt.JWT
import com.auth0.jwt.algorithms.Algorithm

object JwtUtil {
  fun createToken(subject: String, expired: Long, algorithmSecret: String): String {
    val now = DateTimeUtil.now()
    return JWT.create()
      .withSubject(subject)
      .withIssuedAt(DateTimeUtil.toDate(now))
      .withExpiresAt(DateTimeUtil.toDate(now.plusMinutes(expired)))
      .sign(Algorithm.HMAC512(algorithmSecret))
  }

  fun decodeToken(token: String, algorithmSecret: String): String {
    return JWT.require(Algorithm.HMAC512(algorithmSecret))
      .build()
      .verify(token)
      .subject
  }

}