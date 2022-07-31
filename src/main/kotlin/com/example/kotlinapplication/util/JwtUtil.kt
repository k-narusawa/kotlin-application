package com.example.kotlinapplication.util

import com.auth0.jwt.JWT
import com.auth0.jwt.algorithms.Algorithm

object JwtUtil {

  /**
   * JWT形式のトークンを生成
   *
   * @param subject サブジェクト
   * @param expired 有効期限(分単位)
   * @param algorithmSecret
   * @return JWT形式のトークン
   */
  fun createToken(subject: String, expired: Long, algorithmSecret: String): String {
    val now = DateTimeUtil.now()
    return JWT.create()
      .withSubject(subject)
      .withIssuedAt(DateTimeUtil.toDate(now))
      .withExpiresAt(DateTimeUtil.toDate(now.plusMinutes(expired)))
      .sign(Algorithm.HMAC512(algorithmSecret))
  }

  /**
   * トークンを復号
   *
   * @param token トークン
   * @param algorithmSecret
   * @return トークンの復号結果
   */
  fun decodeToken(token: String, algorithmSecret: String): String {
    return JWT.require(Algorithm.HMAC512(algorithmSecret))
      .build()
      .verify(token)
      .subject
  }

}