package com.example.kotlinapplication.domain.service.repository

interface RedisRepository {
  fun findByKey(key: String): String?
  fun save(key: String, value: String)
  fun deleteByKey(key: String)
}