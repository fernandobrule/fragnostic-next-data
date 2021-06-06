package com.fragnostic.nextdata

import com.fragnostic.validator.RutValidator
import org.scalatest.{ FunSpec, Matchers }

trait AbstractTest extends FunSpec with Matchers with NextAlfanum with RutValidator {

}
