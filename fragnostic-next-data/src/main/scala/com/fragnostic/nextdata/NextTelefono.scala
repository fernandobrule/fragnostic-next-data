package com.fragnostic.nextdata

import com.fragnostic.support.FilesSupport
import org.slf4j.{ Logger, LoggerFactory }

import java.io.{ BufferedReader, InputStreamReader }
import scala.annotation.tailrec
import scala.util.Random

trait NextTelefono extends FilesSupport {

  private[this] val logger: Logger = LoggerFactory.getLogger(getClass.getName)

  private lazy val path2brazilDiallingCodes = "/list-of-brazil-dialling-codes.dat"
  private lazy val path2countryDiallingCodes = "/list-of-country-dialling-codes.dat"

  private final def nextRandomBrazilDiallingCode: String =
    Option(getClass getResourceAsStream path2brazilDiallingCodes) map (is =>
      Option(new InputStreamReader(is)) map (isr =>
        Option(new BufferedReader(isr)) map (br => {
          val list: List[String] = bufferedReaderToList(br, List[String]())
          list(Random.nextInt(list.size))
        }) getOrElse {
          logger.error("nextRandomBrazilDiallingCode() - try to get BufferedReader error")
          "ooops"
        }) getOrElse {
        logger.error("nextRandomBrazilDiallingCode() - try to get InputStreamReader error")
        "ooops"
      }) getOrElse {
      logger.error(s"nextRandomBrazilDiallingCode() - try to get InputStream error, path:$path2brazilDiallingCodes")
      "ooops"
    }

  private final def nextRandomCountryDiallingCode: String =
    Option(getClass getResourceAsStream path2countryDiallingCodes) map (is =>
      Option(new InputStreamReader(is)) map (isr =>
        Option(new BufferedReader(isr)) map (br => {
          val list: List[String] = bufferedReaderToList(br, List[String]())
          list(Random.nextInt(list.size))
        }) getOrElse {
          logger.error("nextRandomCountryDiallingCode() - try to get BufferedReader error")
          "ooops"
        }) getOrElse {
        logger.error("nextRandomCountryDiallingCode() - try to get InputStreamReader error")
        "ooops"
      }) getOrElse {
      logger.error(s"nextRandomCountryDiallingCode() - try to get InputStream error, path:$path2brazilDiallingCodes")
      "ooops"
    }

  @tailrec
  private final def nextRandomNumber: Int = {
    val n = 10000000 + new Random().nextInt(90000000)
    if (n < 0) {
      nextRandomNumber
    } else {
      if (n < 10000000) {
        nextRandomNumber
      } else {
        n
      }
    }
  }

  protected def nextRandomBrazilMobileNumberWithCodeArea: String =
    s"55 $nextRandomBrazilMobileNumberWithoutCodeArea"

  protected def nextRandomBrazilMobileNumberWithoutCodeArea: String =
    s"$nextRandomBrazilDiallingCode 9$nextRandomNumber"

}
