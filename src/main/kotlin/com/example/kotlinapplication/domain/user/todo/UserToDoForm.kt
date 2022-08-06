package com.example.kotlinapplication.domain.user.todo

import java.time.LocalDateTime

data class UserToDoForm(
  val todo: String,
  val priority: Priority?,
  val timeLimit: LocalDateTime?,
  val doneFlg: Boolean
)
