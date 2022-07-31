package com.example.kotlinapplication.domain.user

data class UserIssueToken(
  val accessToken: String,
  val refreshToken: String
)
