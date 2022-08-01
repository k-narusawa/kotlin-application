package com.example.kotlinapplication.adapter.infrastructure.repository

import com.example.kotlinapplication.domain.service.repository.RedisRepository
import java.util.concurrent.TimeUnit
import org.springframework.data.redis.core.StringRedisTemplate
import org.springframework.stereotype.Repository


@Repository
class RedisRepositoryImpl(private val redisTemplate: StringRedisTemplate) : RedisRepository {
  override fun findByKey(key: String): String? {
    val ops = redisTemplate.opsForValue()
    return ops.get(key)
  }

  override fun save(key: String, value: String) {
    val ops = redisTemplate.opsForValue()
    ops.set(key, value, 3000L, TimeUnit.SECONDS)
  }

  override fun deleteByKey(key: String) {
    val ops = redisTemplate.opsForValue()
    ops.getAndDelete(key)
  }
}