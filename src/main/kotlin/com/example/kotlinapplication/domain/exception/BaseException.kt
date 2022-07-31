package com.example.kotlinapplication.domain.exception

abstract class BaseException(
  message: String,
  cause: Exception? = null,
  val errorCode: ErrorCode
) : RuntimeException(message, cause)