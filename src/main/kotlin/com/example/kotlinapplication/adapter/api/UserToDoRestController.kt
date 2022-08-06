package com.example.kotlinapplication.adapter.api

import com.example.kotlinapplication.application.UserToDoService
import com.example.kotlinapplication.domain.user.todo.UserToDoDto
import com.example.kotlinapplication.domain.user.todo.UserToDoForm
import java.security.Principal
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
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
  fun getToDos(
    @RequestParam("keyword") keyword: String,
    @RequestParam("doneFlg") doneFlg: Boolean?,
    @RequestParam("limit") limit: Int?,
    @RequestParam("offset") offset: Int?,
    principal: Principal
  ): List<UserToDoDto> {
    val userId = principal.name
    return userToDoService.getToDos(
      userId = userId,
      keyword = keyword,
      doneFlg = doneFlg,
      limit = limit ?: 10,
      offset = offset ?: 0
    )
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

  @DeleteMapping("/{todoId}")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  fun deleteToDo(@PathVariable(value = "todoId") todoId: Long, principal: Principal) {
    val userId = principal.name
    userToDoService.deleteToDo(userId = userId, todoId = todoId)
  }
}