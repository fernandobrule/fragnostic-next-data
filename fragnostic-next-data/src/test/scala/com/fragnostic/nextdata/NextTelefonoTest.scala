package com.fragnostic.nextdata

class NextTelefonoTest extends AbstractTest with NextTelefono {

  describe("Next Telefono Test") {

    it("Can Get Next Random Mobile Num") {
      for (i <- 1 to 10) {
        val mobileNum = nextRandomBrazilMobileNumberWithCodeArea
        logger.info(s"Can Get Next Random Mobile Num - mobileNum: $mobileNum")
        mobileNum.length should be(15)
      }
    }
  }

}
