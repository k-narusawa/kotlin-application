package com.example.kotlinapplication.domain.user.todo

import java.time.LocalDateTime

data class UserToDoEntity(
  val todoId: Long,
  val userId: String,
  val todo: String,
  val timeLimit: LocalDateTime?,
  val priority: Priority?,
  val doneFlg: Boolean,
  val createdAt: LocalDateTime,
  val updatedAt: LocalDateTime
) {
  fun toDto() =
    UserToDoDto(
      todoId = todoId,
      userid = userId,
      todo = todo,
      timeLimit = timeLimit,
      priority = priority,
      doneFlg = doneFlg,
      createdAt = createdAt,
      updatedAt = updatedAt
    )
}
