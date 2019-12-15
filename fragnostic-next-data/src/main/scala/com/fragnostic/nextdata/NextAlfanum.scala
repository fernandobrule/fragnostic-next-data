package com.fragnostic.nextdata

import java.io.{ BufferedReader, InputStreamReader }

import com.fragnostic.support.FilesSupport
import org.slf4j.{ Logger, LoggerFactory }

import scala.util.Random

trait NextAlfanum extends FilesSupport {

  private[this] val logger: Logger = LoggerFactory.getLogger(getClass.getName)

  private val path2names = "/nombres.dat"
  private val path2adjetivos = "/adjetivos.dat"
  private val path2spareparts = "/sparepart.dat"
  private val path2colors = "/colors.dat"

  private val random = Random

  private val rutNums = List(2, 3, 4, 5, 6, 7, 2, 3, 4, 5, 6, 7)

  private def nextRandomString(path: String): String =
    Option(getClass getResourceAsStream (path)) map (
      is => Option(new InputStreamReader(is)) map (
        isr => Option(new BufferedReader(isr)) map (
          br => {
            val list: List[String] = bufferedReaderToList(br, List[String]())
            list(random.nextInt(list.size))
          }) getOrElse {
            logger.error("nextRandomString() - try to get BufferedReader error")
            "ooops"
          }) getOrElse {
          logger.error("nextRandomString() - try to get InputStreamReader error")
          "ooops"
        }) getOrElse {
        logger.error(s"nextRandomString() - try to get InputStream error, path:$path")
        "ooops"
      }

  def nextRandomNom: String =
    nextRandomString(path2names)

  def nextRandomColor: String =
    nextRandomString(path2colors)

  def nextRandomAdj: String =
    nextRandomString(path2adjetivos)

  def nextRandomSparePart: String =
    nextRandomString(path2spareparts)

  def nextRandomEmail(nom: String): String =
    s"$nom@$nextRandomAdj.com"

  private def calculaDigitoVerificador(rutBase: String): String = {
    val mult =
      (rutBase.reverseMap(_.asDigit).toList, rutNums).zipped.map(_ * _)
    val suma = (0 /: mult)(_ + _)
    val resto = suma % 11
    val diff = 11 - resto
    if (diff == 10) "k"
    else diff.toString
  }

  def nextRandomRut: String = {
    var rut: Long = random.nextLong()
    while (rut < 0) {
      rut = random.nextLong()
    }
    // 10308143
    val base = rut.toString.substring(0, 8)
    s"$base-${calculaDigitoVerificador(base)}"
  }

}
