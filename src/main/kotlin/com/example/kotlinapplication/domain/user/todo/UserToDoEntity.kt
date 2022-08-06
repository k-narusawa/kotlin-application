package com.example.kotlinapplication.domain.user.todo

import com.example.kotlinapplication.domain.exception.ApiApplicationException
import com.example.kotlinapplication.domain.exception.ErrorCode
import java.time.LocalDateTime

data class UserToDoEntity(
  val todoId: Long,
  val userId: String,
  val todo: String,
  val timeLimit: LocalDateTime? = null,
  val priority: Priority? = null,
  val doneFlg: Boolean,
  val createdAt: LocalDateTime? = null,
  val updatedAt: LocalDateTime? = null
) {
  fun toDto() =
    UserToDoDto(
      todoId = todoId,
      userid = userId,
      todo = todo,
      timeLimit = timeLimit,
      priority = priority,
      doneFlg = doneFlg,
      createdAt = createdAt ?: throw ApiApplicationException(
        message = "作成日時が取得できません",
        errorCode = ErrorCode.SERVER_ERROR
      ),
      updatedAt = updatedAt ?: throw ApiApplicationException(
        message = "更新日時が取得できません",
        errorCode = ErrorCode.SERVER_ERROR
      ),
    )
}
