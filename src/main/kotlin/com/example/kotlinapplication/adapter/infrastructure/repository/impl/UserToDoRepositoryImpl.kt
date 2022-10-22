package com.example.kotlinapplication.adapter.infrastructure.repository.impl

import com.example.kotlinapplication.adapter.infrastructure.mapper.UserToDoMapper
import com.example.kotlinapplication.adapter.infrastructure.repository.UserToDoRepository
import com.example.kotlinapplication.domain.user.todo.UserToDoEntities
import com.example.kotlinapplication.domain.user.todo.UserToDoEntity
import org.apache.ibatis.session.RowBounds
import org.springframework.stereotype.Repository

@Repository
class UserToDoRepositoryImpl(private val userToDoMapper: UserToDoMapper) : UserToDoRepository {
  override fun findByUserId(userId: String): UserToDoEntities =
    UserToDoEntities(userToDoMapper.findByUserId(userId = userId))

  override fun findByUserIdAndToDoId(userId: String, todoId: Long): UserToDoEntity? =
    userToDoMapper.findByUserIdAndToDoId(userId = userId, todoId = todoId)

  override fun findByUserIdAndKeywordAndDoneFlg(
    userId: String,
    keyword: String?,
    doneFlg: Boolean?,
    rowBounds: RowBounds
  ): UserToDoEntities =
    UserToDoEntities(
      userToDoMapper.findByUserIdAndKeywordAndDoneFlg(
        userId = userId,
        keyword = keyword,
        doneFlg = doneFlg,
        rowBounds = rowBounds
      )
    )


  override fun save(userToDoEntity: UserToDoEntity) {
    userToDoMapper.save(userToDoEntity = userToDoEntity)
  }

  override fun update(userToDoEntity: UserToDoEntity) {
    userToDoMapper.update(userToDoEntity = userToDoEntity)
  }

  override fun deleteByUserIdAndToDoId(userId: String, todoId: Long) {
    userToDoMapper.deleteByUserIdAndToDoId(userId = userId, todoId = todoId)
  }
}