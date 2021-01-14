package com.fragnostic.nextdata

class NextLocaleTest extends AbstractTest with NextLocale {

  describe("") {

    it("Can Get Next Random Web Site") {
      val countryCode: String = nextRandomCountryCode
      val next = nextRandomWebSite(nextRandomNomFant, countryCode)
      println(s"next random web site:\u0027$next\u0027")
    }

  }
}
