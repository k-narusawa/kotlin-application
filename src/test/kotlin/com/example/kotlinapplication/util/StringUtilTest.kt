package com.example.kotlinapplication.util

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

class StringUtilTest {
  @Nested
  inner class UUIDの生成 {
    @Test
    fun UUIDで生成したリストが重複しないこと() {
      val actual = MutableList(10000) { StringUtil.uuidGenerate() }
      assertThat(actual.distinct().size).isEqualTo(10000)
    }
  }
}