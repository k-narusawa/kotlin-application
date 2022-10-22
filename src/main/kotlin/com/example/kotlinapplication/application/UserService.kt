package com.example.kotlinapplication.application

import com.example.kotlinapplication.adapter.infrastructure.repository.RedisRepository
import com.example.kotlinapplication.adapter.infrastructure.repository.UserMasterRepository
import com.example.kotlinapplication.config.Environments
import com.example.kotlinapplication.domain.exception.ApiApplicationException
import com.example.kotlinapplication.domain.exception.ErrorCode
import com.example.kotlinapplication.domain.user.UserIssueToken
import com.example.kotlinapplication.domain.user.UserMasterEntity
import com.example.kotlinapplication.util.DateTimeUtil
import com.example.kotlinapplication.util.JwtUtil
import java.util.*
import org.apache.commons.lang3.RandomStringUtils
import org.springframework.security.core.userdetails.User
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional


@Service
@Transactional
class UserService(
  private val userMasterRepository: UserMasterRepository,
  private val redisRepository: RedisRepository,
  private val environments: Environments
) : UserDetailsService {
  companion object {
    private const val refreshTokenDigit = 10
  }

  @Transactional(readOnly = true)
  override fun loadUserByUsername(loginId: String): UserDetails {
    val userMasterEntity =
      userMasterRepository.findByLoginId(loginId = loginId)
        ?: throw ApiApplicationException(message = "認証失敗", errorCode = ErrorCode.UN_AUTHORIZED)

    return User
      .withUsername(userMasterEntity.userId)
      .password(userMasterEntity.password)
      .authorities(Collections.emptyList()).build()
  }

  /**
   * 認証後JWTトークンを発行
   *
   * @param userId
   * @return トークン情報
   */
  fun issueToken(userId: String): UserIssueToken {
    val token = JwtUtil.createToken(
      subject = userId,
      expired = environments.accessTokenExpired.toLong(),
      algorithmSecret = environments.accessTokenSecret
    )
    redisRepository.save(key = userId, value = token)
    return UserIssueToken(
      accessToken = token,
      refreshToken = generateRefreshToken(userId)
    )
  }

  /**
   * リフレッシュトークンを発行
   *
   * @param userId
   * @return リフレッシュトークン
   */
  private fun generateRefreshToken(userId: String): String {
    val userMasterEntity =
      userMasterRepository.findByUserId(userId = userId)
        ?: throw ApiApplicationException(message = "認証失敗", errorCode = ErrorCode.UN_AUTHORIZED)

    val refreshToken: String =
      RandomStringUtils.randomAlphabetic(refreshTokenDigit)
    val nextUserMasterEntity = UserMasterEntity(
      userId = userMasterEntity.userId,
      loginId = userMasterEntity.loginId,
      password = userMasterEntity.password,
      refreshToken = refreshToken,
      refreshTokenIssueAt = DateTimeUtil.now()
    )
    userMasterRepository.update(userMasterEntity = nextUserMasterEntity)
    return refreshToken
  }

}