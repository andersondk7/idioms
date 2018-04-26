package org.aea.flowers

import org.scalatest.{FunSpec, Matchers}

class FlowersSpec extends FunSpec with Matchers {

  describe("flowers") {
    it("T.4.6 -- more flowers than buyers") {
      Flowers.buy(4, 6, Seq(2,4,6,5,3,1)) shouldBe 24
    }
    it("T.4.3 -- more buyers than flowers") {
      Flowers.buy(4, 3, Seq(2,6,3)) shouldBe 11
    }
    it("T.4.4 -- same buyers as flowers") {
      Flowers.buy(4, 3, Seq(2,6,3,4)) shouldBe 15
    }
    it("T.4.6 -- more flowers than buyer, duplicate cost") {
      Flowers.buy(4, 6, Seq(2,6,3,4,4,3)) shouldBe 27
    }
    it("T.4.20 -- more flowers than buyer, duplicate cost") {
      val costs = Seq(2,4,6,8,1,3,5,7,2,4,6,8,1,3,5,7,9,2,4,8)
      println(s"\ncosts: ${costs.length}\n")
      Flowers.buy(4, costs.length, costs) shouldBe 217
    }
    it("t.80.4 -- test case 7") {
      Flowers.buy(4, 80, Seq(
      780999, 307463, 538091, 620991, 464358, 412141, 468672, 769644,
      683101, 865778, 771022, 149027, 674981, 742164, 843060, 252022,
      681057, 737745, 941805, 930317, 630992, 245238, 745878, 32699,
      153436, 466246, 645069, 425148, 655503, 662211, 133685, 408141,
      727397, 838748, 422485, 730216, 141692, 731378, 345504, 732699,
      747304, 832840, 33679,  1604,   263331, 45064,  465643, 680875,
      40801,  257708, 600999, 451132, 527490, 520015, 215337, 141471,
      692193, 65553,  190121, 362166, 156297, 772384, 493106, 392882,
      563080, 502058, 927750, 668946, 302939, 844572, 904735, 991162,
      301512, 241941, 94287,  562860, 688651, 98284, 416776, 841701)) shouldBe 300504932

    }
  }

}
