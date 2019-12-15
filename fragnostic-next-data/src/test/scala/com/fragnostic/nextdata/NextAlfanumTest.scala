package com.fragnostic.nextdata

import org.scalatest.{ FunSpec, Matchers }

class NextAlfanumTest extends FunSpec with Matchers with NextAlfanum {

  describe("Next Alfanum Test") {

    it("Can get nextRandomSparePart") {
      val next = nextRandomSparePart
      next should not be "ooops"
      println(s"next:$next")
    }

  }
}
