package com.fragnostic.nextdata

class NextAlfanumTest extends AbstractTest {

  describe("Next Alfanum Test") {

    it("Can Get Next Random Password") {
      val maxLength: Int = 36
      val password = nextRandomPsw(maxLength)
      password.length should be(maxLength)
      println(s"next random password:\u0027$password\u0027")
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

  }
}
