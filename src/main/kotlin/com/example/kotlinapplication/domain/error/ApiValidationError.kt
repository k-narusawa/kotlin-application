package com.example.kotlinapplication.domain.error

data class ApiValidationError(
  val message: String,
  val validationInfoList: List<ValidationInfo>
) {
}