package com.fragnostic.nextdata

import com.fragnostic.support.FilesSupport
import org.slf4j.{ Logger, LoggerFactory }

import scala.util.Random

trait NextAlfanum extends FilesSupport {

  private def logger: Logger = LoggerFactory.getLogger(getClass)

  private val path2resources = "fragnostic-next-data/src/main/resources"
  private val path2names = s"$path2resources/nombres.dat"
  private val path2adjetivos = s"$path2resources/adjetivos.dat"
  private val path2spareparts = s"$path2resources/sparepart.dat"
  private val path2colors = s"$path2resources/colors.dat"

  private val random = Random

  private val rutNums = List(2, 3, 4, 5, 6, 7, 2, 3, 4, 5, 6, 7)

  private def nextRandomString(path: String): String =
    readFileToList(path, "UTF-8") fold (
      error => {
        logger.error(s"nexRandomNom() - $error")
        "ooops"
      },
      list => list(random.nextInt(list.size)))

  def nextRandomNom: String = {
    logger.warn("nextRandomNom() - UGLY CODE")
    nextRandomString(path2names)
  }

  def nextRandomColor: String = {
    logger.warn("nextRandomColor() - UGLY CODE")
    nextRandomString(path2colors)
  }

  def nextRandomAdj: String = {
    logger.warn("nextRandomNom() - UGLY CODE")
    nextRandomString(path2adjetivos)
  }

  def nextRandomSparePart: String = {
    logger.warn("nextRandomSparePart() - UGLY CODE")
    nextRandomString(path2spareparts)
  }

  def nextRandomEmail(nom: String): String = s"$nom@$nextRandomAdj.com"

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
