package com.fragnostic.nextdata

import com.fragnostic.support.FilesSupport
import com.fragnostic.validator.RutValidator
import org.slf4j.{ Logger, LoggerFactory }

import java.io.{ BufferedReader, InputStreamReader }
import scala.annotation.tailrec
import scala.util.Random

trait NextAlfanum extends FilesSupport with RutValidator {

  private[this] val logger: Logger = LoggerFactory.getLogger(getClass.getName)

  // https://owasp.org/www-community/password-special-characters
  private lazy val passwordSpecialCharacters: String = """ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789\u0020\u0021\u0022\u0023\u0024\u0025\u0026\u0027\u0028\u0029\u002A\u002B\u002C\u002D\u002E\u002F\u003A\u003B\u003C\u003D\u003E\u003F\u0040\u005B\u005C\u005D\u005E\u005F\u0060\u007B\u007C\u007D\u007E"""
  private lazy val path2adjetivos = "/adjetivos.dat"
  private lazy val path2colors = "/colors.dat"
  private lazy val path2names = "/nombres.dat"
  private lazy val path2spareparts = "/sparepart.dat"
  private lazy val razonPosfix: List[String] = List("S.A.", "Ltda.", "EIRL", "Cia.")

  private final def nextRandomWord(path: String): String =
    Option(getClass getResourceAsStream (path)) map (is =>
      Option(new InputStreamReader(is)) map (isr =>
        Option(new BufferedReader(isr)) map (br => {
          val list: List[String] = bufferedReaderToList(br, List[String]())
          list(Random.nextInt(list.size))
        }) getOrElse {
          logger.error("nextRandomWord() - try to get BufferedReader error")
          "ooops"
        }) getOrElse {
        logger.error("nextRandomWord() - try to get InputStreamReader error")
        "ooops"
      }) getOrElse {
      logger.error(s"nextRandomWord() - try to get InputStream error, path:$path")
      "ooops"
    }

  @tailrec
  private def nextRutRaw: String = {
    val rutRaw: Long = Random.nextLong()
    if (rutRaw < 0) {
      nextRutRaw
    } else {
      val base = rutRaw.toString.substring(0, 8)
      s"$base-${calculaDigitoVerificador(base)}"
    }
  }

  final def nextRandomAdj: String =
    nextRandomWord(path2adjetivos)

  final def nextRandomColor: String =
    nextRandomWord(path2colors)

  final def nextRandomDominio(nomFant: String, countryCode: String): String =
    s"${nomFant.toLowerCase.replaceAll("\\s", "-")}.${countryCode.toLowerCase}"

  final def nextRandomEmail(nom: String, nomFant: String, countryCode: String): String =
    s"${nom.toLowerCase}@${nextRandomDominio(nomFant, countryCode)}"

  final def nextRandomNom: String =
    nextRandomWord(path2names)

  final def nextRandomNomFant: String =
    s"${nextRandomNom.capitalize} ${nextRandomAdj.capitalize}"

  @tailrec
  final def nextRandomUsername: String = {
    val username = s"${nextRandomNom.capitalize}${nextRandomAdj.capitalize}"
    if (username.length < 8) {
      nextRandomUsername
    } else {
      username
    }
  }

  final def nextCharInPsw: Char =
    passwordSpecialCharacters charAt (Random nextInt passwordSpecialCharacters.length)

  final def nextRandomPsw(maxLength: Int): String =
    if (maxLength > 0) {
      s"$nextCharInPsw${nextRandomPsw(maxLength - 1)}"
    } else {
      ""
    }

  final def nextRandomRazon: String =
    s"${nextRandomAdj.capitalize} ${nextRandomAdj.capitalize} ${razonPosfix(Random.nextInt(4))}"

  final def nextRandomRut: String =
    validate(nextRutRaw) fold (error => nextRandomRut,
      rut => rut)

  final def nextRandomSparePart: String =
    nextRandomWord(path2spareparts)
      .split("\\s+")
      .map(word => word.capitalize)
      .mkString(" ")

  final def nextRandomWebSite(nomFant: String, countryCode: String): String =
    s"https://www.${nomFant.toLowerCase.replaceAll("\\s+", ".")}.${countryCode.toLowerCase()}"

}
