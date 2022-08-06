package com.example.kotlinapplication.domain.user.todo

import java.time.LocalDateTime

data class UserToDoEntity(
  val todoId: Long,
  val userid: String,
  val todo: String,
  val deadLine: LocalDateTime?,
  val priority: Priority?,
  val createAt: LocalDateTime,
  val updateAt: LocalDateTime
)
