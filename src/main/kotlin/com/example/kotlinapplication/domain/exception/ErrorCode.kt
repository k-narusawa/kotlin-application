package com.example.kotlinapplication.domain.exception

import org.springframework.http.HttpStatus

enum class ErrorCode(val status: HttpStatus) {
  BAD_REQUEST(HttpStatus.BAD_REQUEST),
  UN_AUTHORIZED(HttpStatus.UNAUTHORIZED),
  SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR),
}