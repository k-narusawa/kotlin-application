package com.example.kotlinapplication.adapter.infrastructure.repository

import com.example.kotlinapplication.domain.user.todo.UserToDoEntities
import com.example.kotlinapplication.domain.user.todo.UserToDoEntity
import org.apache.ibatis.session.RowBounds

interface UserToDoRepository {
  fun findByUserId(userId: String): UserToDoEntities
  fun findByUserIdAndToDoId(userId: String, todoId: Long): UserToDoEntity?
  fun findByUserIdAndKeywordAndDoneFlg(
    userId: String,
    keyword: String?,
    doneFlg: Boolean?,
    rowBounds: RowBounds
  ): UserToDoEntities

  fun save(userToDoEntity: UserToDoEntity)
  fun update(userToDoEntity: UserToDoEntity)
  fun deleteByUserIdAndToDoId(userId: String, todoId: Long)
}
