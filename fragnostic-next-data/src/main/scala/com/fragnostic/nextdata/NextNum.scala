package com.fragnostic.nextdata

import scala.annotation.tailrec
import scala.util.Random

trait NextNum {

  private lazy val FIVE: BigDecimal = BigDecimal("0")
  private lazy val ONE_THOUSAND: BigDecimal = BigDecimal("100000")

  @tailrec
  private final def nextRandomPrice(price: BigDecimal, decimals: Int): BigDecimal = {
    if (FIVE.compare(price) == -1 && ONE_THOUSAND.compare(price) == -1) {
      nextRandomPrice(nextRandomBigDecimal, decimals)
    } else {
      price.setScale(decimals, BigDecimal.RoundingMode.UP)
    }
  }

  // 56 9 79797381
  // 55 11 9 51976773
  @tailrec
  final def nextLongPositivo: Long = {
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

  final def nextRandomBigDecimal: BigDecimal = BigDecimal(Random.nextDouble() * Random.nextInt(100000))

  final def nextRandomPrice(decimals: Int): BigDecimal =
    nextRandomPrice(nextRandomBigDecimal, decimals)

}
