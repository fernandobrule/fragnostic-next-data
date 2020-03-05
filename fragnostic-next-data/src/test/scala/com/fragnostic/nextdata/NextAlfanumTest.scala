package com.fragnostic.nextdata

import com.fragnostic.validator.RutValidator
import org.scalatest.{ FunSpec, Matchers }

class NextAlfanumTest extends FunSpec with Matchers with NextAlfanum with RutValidator {

  describe("Next Alfanum Test") {

    it("Can Get Next Random Password") {
      val maxLength: Int = 36
      val next = nextRandomPsw(maxLength)
      next.length should be(maxLength)
      println(s"next random password:\u0027$next\u0027")
    }

    it("Can Get Next Random Rut") {
      val next = nextRandomRut

      val fmtRut: String = validate(next) fold (
        error => throw new IllegalStateException(error),
        rut => rut)

      fmtRut should be(next)

      println(s"next random rut:\u0027$next\u0027")

    }

    it("Can Get Next Random Spare Part") {
      val next = nextRandomSparePart
      next should not be "ooops"
      println(s"next random spare part:\u0027$next\u0027")
    }

    it("Can Get Next Random Web Site") {
      val next = nextRandomWebSite(nextRandomNomFant)
      println(s"next random web site:\u0027$next\u0027")
    }

  }
}
