package org.aea.recursion

import scala.annotation.tailrec

/**
  */
object NaturalE {

  def pow(x: BigDecimal)(implicit maxTerms:Int): BigDecimal = {
    @tailrec
    def calcTerm(count: Int, value: BigDecimal, power: BigDecimal, factorial: BigDecimal): (BigDecimal, BigDecimal, BigDecimal) = {
      if (count == maxTerms+1) (value, count, factorial)
      else {
        val exp = x * power
        val f = count * factorial
        val term = exp / f
        val answer = value + term
//        println(s"$count\t$exp\t$f\t$term")
        calcTerm(count + 1, answer, exp, f)
      }
    }
    val answer = calcTerm(1, 1, 1, 1)._1
//    println(answer)
    answer
  }

}
