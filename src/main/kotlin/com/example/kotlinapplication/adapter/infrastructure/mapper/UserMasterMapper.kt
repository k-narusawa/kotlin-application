package com.example.kotlinapplication.adapter.infrastructure.mapper

import com.example.kotlinapplication.domain.user.UserMasterEntity
import org.apache.ibatis.annotations.Mapper

@Mapper
interface UserMasterMapper {
  fun findByUserId(userId: String): UserMasterEntity?
  fun findByLoginId(loginId: String): UserMasterEntity?
  fun save(userMasterEntity: UserMasterEntity)
  fun update(userMasterEntity: UserMasterEntity)
}