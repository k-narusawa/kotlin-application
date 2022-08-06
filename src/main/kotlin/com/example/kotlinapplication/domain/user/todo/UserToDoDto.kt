package com.example.kotlinapplication.domain.user.todo

import java.time.LocalDateTime

data class UserToDoDto(
  val todoId: Long,
  val userid: String,
  val todo: String,
  val timeLimit: LocalDateTime?,
  val priority: Priority?,
  val doneFlg: Boolean,
  val createdAt: LocalDateTime,
  val updatedAt: LocalDateTime
)
