package org.aea.recursion

import org.scalatest.{FunSpec, Matchers}

/**
  */
class NaturalESpec extends FunSpec with Matchers {

  def round(x: BigDecimal)(implicit margin: Int) = x.setScale(margin, BigDecimal.RoundingMode.HALF_UP)

  val maxTerms = 20
  describe("naturalE") {
    it("should work of power of 2") {
      implicit val margin: Int = 4
      val expected:BigDecimal = round(7.38905609893)
      val actual = round(NaturalE.pow(2)(maxTerms))
      expected shouldBe actual
    }
    it("should work of power of 1/2") {
      implicit val margin: Int = 4
      val expected:BigDecimal = round(1.6487212707)
      val actual = round(NaturalE.pow(.5)(maxTerms))
      actual shouldBe expected
    }
    it("should work of power of -1/2") {
      implicit val margin: Int = 4
      val expected:BigDecimal = round(0.6065306597)
      val actual = round(NaturalE.pow(-0.5)(maxTerms))
      actual shouldBe expected
    }
    it("should work of power of -2") {
      implicit val margin: Int = 4
      val expected:BigDecimal = round(0.13533528323)
      val actual = round(NaturalE.pow(-2)(maxTerms))
      actual shouldBe expected
    }
    it("should work of power of 3") {
      implicit val margin: Int = 4
      val expected:BigDecimal = round(20.0855369232)
      val actual = round(NaturalE.pow(3)(maxTerms))
      actual shouldBe expected
    }
    it("should work of power of 4") {
      implicit val margin: Int = 5
      val expected:BigDecimal = round(54.5981500331) // per calculator
      val actual = round(NaturalE.pow(4)(maxTerms))
      actual shouldBe expected
    }
//    it("should work of power of 5") {
//      implicit val margin: Int = 6
//      val expected:BigDecimal = round(148.41315902576603421118004) // per phone calculator
//      val actual = round(NaturalE.pow(5)(maxTerms))
//      actual shouldBe expected
//    }
    it("should work of power of 10") {
      implicit val margin: Int = 6
      val expected:BigDecimal = round(22026.465794806716516957900645) // per phone calculator
      val actual = round(NaturalE.pow(10)(40))
      actual shouldBe expected
    }
//    it("should work of power of -5") {
//      implicit val margin: Int = 6
//      val expected:BigDecimal = round(.0067379469990854670966360484) // per phone calculator
//      val actual = round(NaturalE.pow(-5)(maxTerms))
//      actual shouldBe expected
//    }
  }

}
