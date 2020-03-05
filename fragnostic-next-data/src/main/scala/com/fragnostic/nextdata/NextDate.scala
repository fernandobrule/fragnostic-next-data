package com.fragnostic.nextdata

import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

trait NextDate {

  lazy val pattern = "dd-MM-yyyy HH:mm:ss"
  lazy val dateTimeFormatter: DateTimeFormatter = DateTimeFormatter.ofPattern(pattern)

  final def nextDataHora: String =
    LocalDateTime.now().format(dateTimeFormatter)

  final def nextDataHora(plus: Long): (String, String) = {
    val ldt = LocalDateTime.now()
    (ldt.format(dateTimeFormatter), ldt.plusHours(plus).format(dateTimeFormatter))
  }

}
