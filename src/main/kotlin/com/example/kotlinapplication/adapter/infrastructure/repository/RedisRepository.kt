package com.example.kotlinapplication.adapter.infrastructure.repository

interface RedisRepository {
  fun findByKey(key: String): String?
  fun save(key: String, value: String)
  fun deleteByKey(key: String)
}