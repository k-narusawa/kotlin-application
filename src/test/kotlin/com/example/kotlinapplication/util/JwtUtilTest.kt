package com.example.kotlinapplication.util

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class JwtUtilTest {
  @Nested
  inner class JWT形式のトークンを生成 {
    @Test
    fun JWT形式のトークンを生成できる() {
      val actual = JwtUtil.createToken(
        subject = "subject",
        expired = 0L,
        algorithmSecret = "secret"
      )
      assertThat(actual).isNotBlank
      assertThat(actual).isNotEmpty
      assertThat(actual).isNotNull
    }
  }

  @Nested
  inner class JWT形式のトークンを復号 {
    @Test
    fun JWT形式のトークンを復号できる() {
      val token = JwtUtil.createToken(
        subject = "subject",
        expired = 1L,
        algorithmSecret = "secret"
      )
      val actual = JwtUtil.decodeToken(token = token, algorithmSecret = "secret")
      assertThat(actual).isEqualTo("subject")
    }
  }

  @Test
  fun 期限の切れたトークンを復号できないこと() {
    val token = JwtUtil.createToken(
      subject = "subject",
      expired = -1L, // 有効期限を1分前に設定
      algorithmSecret = "secret"
    )

    assertThrows<Exception> {
      JwtUtil.decodeToken(
        token = token,
        algorithmSecret = "secret"
      )
    }
  }
}