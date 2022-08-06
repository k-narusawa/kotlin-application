package com.example.kotlinapplication.adapter.api

import com.example.kotlinapplication.application.UserToDoService
import com.example.kotlinapplication.domain.user.todo.UserToDoDto
import java.security.Principal
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
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
}