package com.example.kotlinapplication.karate

import com.intuit.karate.junit5.Karate

class KarateTest {
  @Karate.Test
  fun testAll(): Karate? {
    return Karate.run().relativeTo(javaClass)
  }
}