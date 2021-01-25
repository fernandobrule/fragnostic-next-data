package com.fragnostic.nextdata

import java.util.Locale
import scala.util.Random

trait NextLocale {

  private lazy val localeEsCl: Locale = new Locale.Builder()
    .setLanguage("es")
    .setRegion("CL")
    .build()

  private lazy val localeEnUs: Locale = new Locale.Builder()
    .setLanguage("en")
    .setRegion("US")
    .build()

  private lazy val localePtBr: Locale = new Locale.Builder()
    .setLanguage("pt")
    .setRegion("BR")
    .build()

  //
  // https://en.wikipedia.org/wiki/ISO_3166-1
  // Alpha-2 code
  //
  protected val countryCodes: List[String] = List("BR", "CL", "US")
  protected def nextRandomCountryCode: String = countryCodes(new Random().nextInt(countryCodes.length))

  lazy val locales: List[Locale] = List(localeEsCl, localeEnUs, localePtBr)
  lazy val localesMap: Map[String, Locale] = Map("CL" -> localeEsCl, "BR" -> localePtBr, "US" -> localeEnUs)

  def getLocale(countryCode: String): Locale =
    localesMap.get(countryCode) map (locale => locale) getOrElse (localeEsCl)

  final def nextRandomLocale: Locale =
    locales(Random.nextInt(3))

}
