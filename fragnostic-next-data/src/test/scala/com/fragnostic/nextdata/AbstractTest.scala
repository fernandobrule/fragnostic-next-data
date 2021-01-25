package com.fragnostic.nextdata

import com.fragnostic.validator.RutValidator
import org.scalatest.{ FunSpec, Matchers }
import org.slf4j.{ Logger, LoggerFactory }

trait AbstractTest extends FunSpec with Matchers with NextAlfanum with RutValidator {

  protected[this] val logger: Logger = LoggerFactory.getLogger(getClass.getName)

}
