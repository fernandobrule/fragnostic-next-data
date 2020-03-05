package com.fragnostic.validator

import org.slf4j.{ Logger, LoggerFactory }

trait RutValidator {

  private[this] val logger: Logger = LoggerFactory.getLogger(getClass.getName)

  private lazy val k = "k"
  private lazy val rutNums = List(2, 3, 4, 5, 6, 7, 2, 3, 4, 5, 6, 7)

  def calculaDigitoVerificador(rol: String): String = {
    val mult = (rol.reverseMap(_.asDigit).toList, rutNums).zipped.map(_ * _)
    val suma = (0 /: mult)(_ + _)
    val resto = suma % 11
    val diff = 11 - resto
    if (diff == 10) {
      "k"
    } else {
      diff.toString
    }
  }

  private def isValidContraDv(rol: Long, dv: String): Boolean =
    dv.equals(calculaDigitoVerificador(rol.toString))

  def validate(rawRut: String): Either[String, String] = {
    if (logger.isInfoEnabled) logger.info(s"validate() - rawRut[$rawRut]")
    if (!rawRut.trim.isEmpty) {

      val rutFiltrado = rawRut.trim.filter(p => p.isDigit || p.toString.toLowerCase.equals(k)).toLowerCase
      if (logger.isInfoEnabled) logger.info(s"validate() - rutFiltrado[$rutFiltrado]")

      val lenght = rutFiltrado.length

      if (lenght >= 8) {
        if (logger.isInfoEnabled) logger.info(s"validate() - largo valido: $rutFiltrado")
        val base = rutFiltrado.substring(0, lenght - 1).toInt
        val dig = rutFiltrado.substring(lenght - 1)
        if (isValidContraDv(base, dig)) {
          Right(s"$base-$dig")
        } else {
          Left("rut.nv")
        }

      } else {
        if (logger.isInfoEnabled) logger.info(s"validate() - largo NO valido: $rutFiltrado")
        Left("rut.mal.constituido")
      }

    } else {
      Left("rut.empty")
    }
  }

}
