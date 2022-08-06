package com.example.kotlinapplication.adapter.infrastructure.mapper

import com.example.kotlinapplication.domain.user.todo.UserToDoEntity
import org.apache.ibatis.annotations.Mapper

@Mapper
interface UserToDoMapper {
  fun findByUserId(userId: String): List<UserToDoEntity>
  fun findByToDoId(todoId: Long): UserToDoEntity?
  fun save(userToDoEntity: UserToDoEntity)
  fun update(userToDoEntity: UserToDoEntity)
}