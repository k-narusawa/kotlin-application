package com.example.kotlinapplication.adapter.api

import com.example.kotlinapplication.application.ToDoService
import com.example.kotlinapplication.domain.service.repository.UserMasterRepository
import com.example.kotlinapplication.domain.todo.ToDoDto
import java.security.Principal
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController


@RestController
@RequestMapping("/api/todos")
class ToDoRestController(
  private val toDoService: ToDoService,
  private val userMasterRepository: UserMasterRepository
) {

  /**
   * ToDoの一覧を取得
   *
   * @return ToDoのリスト
   */
  @GetMapping
  fun getToDos(principal: Principal): ToDoDto {
    val userId = principal.name
    return ToDoDto(
      todo = "todo",
      priority = 1
    )
  }
}