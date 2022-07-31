package com.example.kotlinapplication.domain.error

import com.example.kotlinapplication.domain.exception.ErrorCode
import org.springframework.http.HttpStatus

data class ApiApplicationError(
  val status: HttpStatus,
  val message: String,
  val errorCode: ErrorCode
) {
}