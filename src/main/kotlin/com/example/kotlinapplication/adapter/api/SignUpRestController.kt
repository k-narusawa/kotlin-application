package com.example.kotlinapplication.adapter.api

import com.example.kotlinapplication.application.SignUpService
import com.example.kotlinapplication.domain.user.UserSignUpForm
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/signup")
class SignUpRestController(private val signUpService: SignUpService) {

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  fun signUp(@RequestBody userSignUpForm: UserSignUpForm) {
    signUpService.signIn(loginId = userSignUpForm.loginId, password = userSignUpForm.password)
  }
}