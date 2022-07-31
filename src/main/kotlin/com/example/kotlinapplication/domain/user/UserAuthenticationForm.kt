package com.example.kotlinapplication.domain.user

import com.fasterxml.jackson.annotation.JsonCreator
import com.fasterxml.jackson.annotation.JsonProperty

data class UserAuthenticationForm
@JsonCreator
constructor(
  @JsonProperty("loginId")
  val loginId: String,

  @JsonProperty("password")
  val password: String
)