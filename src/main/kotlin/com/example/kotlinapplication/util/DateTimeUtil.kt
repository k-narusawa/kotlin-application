package com.example.kotlinapplication.util

import java.time.Instant
import java.time.LocalDateTime
import java.time.ZoneId
import java.time.ZonedDateTime
import java.util.*


object DateTimeUtil {
  fun now(): LocalDateTime =
    LocalDateTime.now()

  fun toDate(localDateTime: LocalDateTime?): Date {
    val zone = ZoneId.systemDefault()
    val zonedDateTime = ZonedDateTime.of(localDateTime, zone)
    val instant: Instant = zonedDateTime.toInstant()
    return Date.from(instant)
  }
}