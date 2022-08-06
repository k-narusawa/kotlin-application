package com.example.kotlinapplication.adapter.api

import com.example.kotlinapplication.application.UserToDoService
import com.example.kotlinapplication.domain.user.todo.UserToDoDto
import com.example.kotlinapplication.domain.user.todo.UserToDoForm
import java.security.Principal
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController


@RestController
@RequestMapping("/api/todos")
class UserToDoRestController(
  private val userToDoService: UserToDoService
) {

  /**
   * ToDoの一覧を取得
   *
   * @return ToDoのリスト
   */
  @GetMapping
  fun getToDos(principal: Principal): List<UserToDoDto> {
    val userId = principal.name
    return userToDoService.getToDos(userId = userId)
  }

  @GetMapping("/{todoId}")
  fun getToDo(@PathVariable(value = "todoId") todoId: Long, principal: Principal): UserToDoDto? {
    val userId = principal.name
    return userToDoService.getToDo(userId = userId, todoId = todoId)
  }

  @PutMapping("/{todoId}")
  fun updateToDO(
    @PathVariable(value = "todoId") todoId: Long,
    @RequestBody userToDoForm: UserToDoForm,
    principal: Principal
  ) {
    val userId = principal.name
    userToDoService.updateToDo(
      userId = userId,
      todoId = todoId,
      todo = userToDoForm.todo,
      priority = userToDoForm.priority,
      timeLimit = userToDoForm.timeLimit,
      doneFlg = userToDoForm.doneFlg
    )
  }

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  fun saveToDo(@RequestBody userToDoForm: UserToDoForm, principal: Principal) {
    val userId = principal.name
    userToDoService.saveToDo(
      userId = userId,
      todo = userToDoForm.todo,
      priority = userToDoForm.priority,
      timeLimit = userToDoForm.timeLimit,
      doneFlg = userToDoForm.doneFlg
    )
  }
}