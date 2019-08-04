package com.fragnostic.nextdata

import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

trait NextDate {

  val pattern = "dd-MM-yyyy HH:mm:ss"
  val dateTimeFormatter: DateTimeFormatter = DateTimeFormatter.ofPattern(pattern)

  def nextDataHora: String =
    LocalDateTime.now().format(dateTimeFormatter)

  def nextDataHora(plus: Long): (String, String) = {
    val ldt = LocalDateTime.now()
    (ldt.format(dateTimeFormatter), ldt.plusHours(plus).format(dateTimeFormatter))
  }

}
