package com.example.kotlinapplication.domain.error

data class ValidationInfo(
  val field: String,
  val rejectedValue: String
)
