package com.example.kotlinapplication.application

import com.example.kotlinapplication.adapter.infrastructure.repository.UserToDoRepository
import com.example.kotlinapplication.domain.user.todo.Priority
import com.example.kotlinapplication.domain.user.todo.UserToDoDto
import com.example.kotlinapplication.domain.user.todo.UserToDoEntity
import java.time.LocalDateTime
import org.apache.ibatis.session.RowBounds
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional
class UserToDoService(private val userToDoRepository: UserToDoRepository) {
  /**
   * 会員に紐づくToDoの一覧を取得
   *
   * @param userId
   * @return ToDoのリスト
   */
  @Transactional(readOnly = true)
  fun getToDos(
    userId: String,
    keyword: String,
    doneFlg: Boolean?,
    limit: Int,
    offset: Int
  ): List<UserToDoDto> {
    val rowBounds = RowBounds(offset, limit)
    val userToDoEntities = userToDoRepository.findByUserIdAndKeywordAndDoneFlg(
      userId = userId,
      keyword = keyword,
      doneFlg = doneFlg,
      rowBounds = rowBounds
    )
    return userToDoEntities.value.map { it.toDto() }
  }

  @Transactional(readOnly = true)
  fun getToDo(userId: String, todoId: Long): UserToDoDto? {
    return userToDoRepository.findByUserIdAndToDoId(userId = userId, todoId = todoId)?.toDto()
  }

  fun updateToDo(
    userId: String,
    todoId: Long,
    todo: String,
    priority: Priority?,
    timeLimit: LocalDateTime?,
    doneFlg: Boolean
  ) {
    val userToDoEntity = UserToDoEntity(
      todoId = todoId,
      userId = userId,
      todo = todo,
      timeLimit = timeLimit,
      priority = priority,
      doneFlg = doneFlg,
    )
    userToDoRepository.update(userToDoEntity = userToDoEntity)
  }

  fun saveToDo(
    userId: String,
    todo: String,
    priority: Priority?,
    timeLimit: LocalDateTime?,
    doneFlg: Boolean
  ) {
    val userToDoEntity = UserToDoEntity(
      todoId = null,
      userId = userId,
      todo = todo,
      timeLimit = timeLimit,
      priority = priority,
      doneFlg = doneFlg
    )
    userToDoRepository.save(userToDoEntity = userToDoEntity)
  }

  fun deleteToDo(userId: String, todoId: Long) {
    userToDoRepository.deleteByUserIdAndToDoId(userId = userId, todoId = todoId)
  }
}