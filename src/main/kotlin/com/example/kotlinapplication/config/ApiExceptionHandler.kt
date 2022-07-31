package com.example.kotlinapplication.config

import com.example.kotlinapplication.domain.error.ApiApplicationError
import com.example.kotlinapplication.domain.error.ApiValidationError
import com.example.kotlinapplication.domain.error.ValidationInfo
import com.example.kotlinapplication.domain.exception.ApiApplicationException
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.validation.FieldError
import org.springframework.validation.ObjectError
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice

@RestControllerAdvice(basePackages = ["com.example.kotlinapplication.adapter.api"])
class ApiExceptionHandler {

  /**
   * バリデーションエラーのハンドリング
   *
   * @param exception
   * @return
   */
  @ExceptionHandler(MethodArgumentNotValidException::class)
  fun handleMethodArgumentNotValid(exception: MethodArgumentNotValidException): ResponseEntity<Any> {
    val validationInfoList = exception.bindingResult.allErrors
      .mapNotNull { error ->
        when (error) {
          is FieldError -> ValidationInfo(error.field, error.defaultMessage ?: "")
          is ObjectError -> ValidationInfo(error.objectName, error.defaultMessage ?: "")
          else -> null
        }
      }
      .toList()
    val error =
      ApiValidationError(message = "validation error", validationInfoList = validationInfoList)
    return ResponseEntity(error, HttpStatus.BAD_REQUEST)
  }

  @ExceptionHandler(ApiApplicationException::class)
  fun handleApiApplicationException(ex: ApiApplicationException): ResponseEntity<Any> {
    val error = ApiApplicationError(
      status = ex.errorCode.status,
      message = ex.message ?: "",
      errorCode = ex.errorCode
    )
    return ResponseEntity(error, HttpStatus.NOT_FOUND)
  }
}