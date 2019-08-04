package com.fragnostic.nextdata

import scala.util.Random

trait NextNum {

  // 979797381
  def nextLongPositivo: Long = {
    val n = new Random().nextLong()
    if (n < 0) {
      -1 * n
    } else {
      if (n < 999999999) {
        nextLongPositivo
      } else {
        n
      }
    }
  }

  def nextTelefono: String = {
    val left = nextLongPositivo.toString.substring(0, 4)
    val right = nextLongPositivo.toString.substring(0, 4)
    s"9$left$right"
  }

}
