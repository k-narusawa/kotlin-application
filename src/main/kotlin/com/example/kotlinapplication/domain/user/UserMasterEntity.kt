package com.example.kotlinapplication.domain.user

import java.time.LocalDateTime

data class UserMasterEntity(
  val userId: String,
  val loginId: String,
  val password: String,
  val refreshToken: String?,
  val refreshTokenIssueAt: LocalDateTime?
)