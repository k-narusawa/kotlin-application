package com.example.kotlinapplication.domain.service.repository

import com.example.kotlinapplication.domain.user.UserMasterEntity

interface UserMasterRepository {
  fun findByUserId(userId: String): UserMasterEntity?
  fun findByLoginId(loginId: String): UserMasterEntity?
  fun save(userMasterEntity: UserMasterEntity)
  fun update(userMasterEntity: UserMasterEntity)
}