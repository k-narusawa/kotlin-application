package com.example.kotlinapplication.util

import java.util.*

object StringUtil {

  /**
   * UUIDを生成する
   *
   * @return UUID
   */
  fun uuidGenerate() = UUID.randomUUID().toString()
}