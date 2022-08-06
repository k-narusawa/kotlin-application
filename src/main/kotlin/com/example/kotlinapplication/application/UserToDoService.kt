package com.example.kotlinapplication.application

import com.example.kotlinapplication.domain.service.repository.UserToDoRepository
import com.example.kotlinapplication.domain.user.todo.UserToDoDto
import org.springframework.stereotype.Service

@Service
class UserToDoService(private val userToDoRepository: UserToDoRepository) {
  /**
   * 会員に紐づくToDoの一覧を取得
   *
   * @param userId
   * @return ToDoのリスト
   */
  fun getToDos(userId: String): List<UserToDoDto> {
    val userToDoEntities = userToDoRepository.findByUserId(userId = userId)
    return userToDoEntities.value.map { it.toDto() }
  }
}