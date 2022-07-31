package com.example.kotlinapplication.domain.exception

class ApiApplicationException : BaseException {

  /**
   * causeあり
   */
  constructor(
    message: String,
    cause: Exception,
    errorCode: ErrorCode
  ) : super(
    message = message,
    cause = cause,
    errorCode = errorCode
  )

  /**
   * causeなし
   */
  constructor(
    message: String,
    errorCode: ErrorCode
  ) : super(
    message = message, errorCode = errorCode
  )

}
