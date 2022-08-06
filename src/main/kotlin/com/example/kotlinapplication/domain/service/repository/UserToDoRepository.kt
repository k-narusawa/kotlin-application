package com.example.kotlinapplication.domain.service.repository

import com.example.kotlinapplication.domain.user.todo.UserToDoEntities
import com.example.kotlinapplication.domain.user.todo.UserToDoEntity

interface UserToDoRepository {
  fun findByUserId(userId: String): UserToDoEntities
  fun findByToDoId(todoId: Long): UserToDoEntity?
  fun save(userToDoEntity: UserToDoEntity)
  fun update(userToDoEntity: UserToDoEntity)
}
