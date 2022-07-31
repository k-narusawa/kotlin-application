package com.example.kotlinapplication.adapter.infrastructure.repository

import com.example.kotlinapplication.adapter.infrastructure.mapper.UserMasterMapper
import com.example.kotlinapplication.domain.service.repository.UserMasterRepository
import com.example.kotlinapplication.domain.user.UserMasterEntity
import org.springframework.stereotype.Repository

@Repository
class UserMasterRepositoryImpl(private val userMasterMapper: UserMasterMapper) :
  UserMasterRepository {
  override fun findByUserId(userId: String): UserMasterEntity? {
    return userMasterMapper.findByUserId(userId = userId)
  }

  override fun findByLoginId(loginId: String): UserMasterEntity? {
    return userMasterMapper.findByLoginId(loginId = loginId)
  }

  override fun save(userMasterEntity: UserMasterEntity) {
    userMasterMapper.save(userMasterEntity = userMasterEntity)
  }

  override fun update(userMasterEntity: UserMasterEntity) {
    userMasterMapper.update(userMasterEntity = userMasterEntity)
  }
}