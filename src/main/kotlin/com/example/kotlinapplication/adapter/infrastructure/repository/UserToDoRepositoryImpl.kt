package com.example.kotlinapplication.adapter.infrastructure.repository

import com.example.kotlinapplication.adapter.infrastructure.mapper.UserToDoMapper
import com.example.kotlinapplication.domain.service.repository.UserToDoRepository
import com.example.kotlinapplication.domain.user.todo.UserToDoEntities
import com.example.kotlinapplication.domain.user.todo.UserToDoEntity
import org.springframework.stereotype.Repository

@Repository
class UserToDoRepositoryImpl(private val userToDoMapper: UserToDoMapper) : UserToDoRepository {
  override fun findByUserId(userId: String): UserToDoEntities =
    UserToDoEntities(userToDoMapper.findByUserId(userId = userId).map { it })
  
  override fun findByToDoId(todoId: Long): UserToDoEntity? =
    userToDoMapper.findByToDoId(todoId = todoId)

  override fun save(userToDoEntity: UserToDoEntity) {
    userToDoMapper.save(userToDoEntity = userToDoEntity)
  }

  override fun update(userToDoEntity: UserToDoEntity) {
    userToDoMapper.update(userToDoEntity = userToDoEntity)
  }
}