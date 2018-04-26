package org.aea.flowers

/**
  * problem:
  * A florist charges repeat customers more by the formula chargedPrice = (previousFlowersBought + 1) * listPrice.
  *
  * For example if you buy a flower that costs $5, then a $3 flower, then a $6 flower the charged prices are:
  * 1. (0 + 1) * 5 =  5  // (previous flowers + current flower) * cost
  * 2. (1 + 1) * 3 =  6 // (previous flowers + current flower) * cost
  * 2. (2 + 1) * 6 = 18 // (previous flowers + current flower) * cost
  * 3. total         29
  *
  * but if you bought the $6, then the $5, then the $3, the charged prices are:
  * 1. (0 + 1) * 6 =  6  // (previous flowers + current flower) * cost
  * 2. (1 + 1) * 5 = 10 // (previous flowers + current flower) * cost
  * 2. (2 + 1) * 3 =  9 // (previous flowers + current flower) * cost
  * 3. total         25
  *
  * so it is better to buy the more expensive flowers first.
  *
  * To add some complexity, the problem to be solved is:
  * 1. given b buyers
  * 1. who buy f flowers
  * 1. at c1, c2, ... cn cost
  *
  * what is the cheapest cost?
  *
  * the solution is to buy the most expensive flowers first, so that the increased surcharge
  * for repeated purchases is applied to the cheapest flowers, so each buyer buys the most
  * expensive remaining flower and then buys the next most expensive etc.
  *
  */
object Flowers {

  def buy(buyerCount: Int, flowerCount: Int, prices: Seq[Int]): Long = {
    println(s"sum\tbuyer\tpreviousPerBuyer\tcost\tcharge\ttotalBought")
    prices.sorted.reverse.foldLeft[(Long, Int, Int, Int)](0, 0, 0, 1)( (sumIndex, cost) => {
      val (sum, previousCount, index, buyer) = sumIndex
      val shouldUpdate = index % buyerCount == 0
      val previousPerBuyer = if (shouldUpdate) previousCount + 1 else previousCount
      val charge = previousPerBuyer * cost
      val total = sum + charge
      // debugging vars ...
      val totalBought = index + 1
      val updatedBuyer = if (shouldUpdate) 1 else buyer + 1
      println(s"$sum\t$buyer\t$previousPerBuyer\t$cost\t$charge\t${index+1}")
      // end debugging

      (total, previousPerBuyer, index+1, updatedBuyer)

    })._1 // only return the sum...
  }

}
