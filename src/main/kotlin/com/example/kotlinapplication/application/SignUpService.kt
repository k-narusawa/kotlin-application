package com.example.kotlinapplication.application

import com.example.kotlinapplication.adapter.infrastructure.repository.UserMasterRepository
import com.example.kotlinapplication.config.Environments
import com.example.kotlinapplication.config.PasswordConfig
import com.example.kotlinapplication.domain.exception.ApiApplicationException
import com.example.kotlinapplication.domain.exception.ErrorCode
import com.example.kotlinapplication.domain.user.UserMasterEntity
import com.example.kotlinapplication.util.StringUtil
import org.springframework.stereotype.Service

@Service
class SignUpService(
  private val userMasterRepository: UserMasterRepository,
  private val passwordConfig: PasswordConfig,
  private val environments: Environments,
) {

  fun signIn(loginId: String, password: String) {
    val userMasterEntity = UserMasterEntity(
      userId = StringUtil.uuidGenerate(),
      loginId = loginId,
      password = passwordConfig.passwordEncoder().encode(password),
      refreshToken = null,
      refreshTokenIssueAt = null
    )
    val registeredUserMasterEntity = userMasterRepository.findByLoginId(loginId = loginId)
    if (registeredUserMasterEntity != null)
      throw ApiApplicationException(
        message = "ログインIDはすでに使用されています",
        errorCode = ErrorCode.LOGIN_ID_ALREADY_USED
      )
    userMasterRepository.save(userMasterEntity = userMasterEntity)
  }

}