package org.aea.maxmin

import org.scalatest.{FunSpec, Matchers}

class MaxMinSpec extends FunSpec with Matchers{

  describe("MaxMin") {
    it ("T1") {
      val size = 2
      val array = Array(1,4,7,2)
     MaxMin.minMax(size, array) shouldBe 1
    }
    it ("T2") {
      val size = 3
      val array = Array(3,10,100,300,200,1000,20,30)
      MaxMin.minMax(size, array) shouldBe 17
    }
    it ("T3") {
      val size = 3
      val array = Array(7,5,6,6,2,1,6)
      MaxMin.minMax(size, array) shouldBe 0
    }
  }
}
