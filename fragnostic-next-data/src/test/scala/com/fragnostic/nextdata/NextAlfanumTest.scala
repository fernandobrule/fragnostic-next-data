package com.fragnostic.nextdata

class NextAlfanumTest extends AbstractTest {

  describe("Next Alfanum Test") {

    it("Can Get Next Random Password") {
      val maxLength: Int = 36
      val password = nextRandomPsw(maxLength)
      password.length should be(maxLength)
    }

    it("Can Get Next Random Rut") {
      val next = nextRandomRut

      val fmtRut: String = validate(next) fold (
        error => throw new IllegalStateException(error),
        rut => rut)

      fmtRut should be(next)
    }

    it("Can Get Next Random Spare Part") {
      val next = nextRandomSparePart
      next should not be "ooops"
    }

  }
}
