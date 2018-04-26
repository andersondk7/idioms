package org.aea.maxmin

/**
  * Given an array of integers and a new size, create an array of integers of that size
  * which will have the smallest variance between max and min
  *
  * examples:
  * 1. given (1,4,7,2) and a new size of 2, the 2 array with the smallest variance would be (1,2)
  * 1. given (3, 10,100,300,200,1000,20,30) and a new size of 3, the array with the smallest variance would be (3,10,20)
  * 1. given (7,5,6,6,2,1,6) and a new size of 3, the array with the smallest variance would be (6,6,6)
  */
object MaxMin {

  def minMax(newSize: Int, initialArray: Array[Int]): Int = {
    val sorted = initialArray.sorted
    sorted.foldLeft[(Int, Int, Int, Array[Int])](0, sorted.length, Int.MaxValue, sorted)( (acc, e) => {
      val (index, length, variance, remaining) = acc
      if (length < newSize) {
        (index + 1, length - 1, variance, remaining.tail)
      }
      else {
        val min = remaining.head
        val max = remaining(newSize-1)
        val delta = max - min
        val result = if (delta < variance) (index+1, length-1, delta, remaining.tail)
        else (index+1, length-1, variance, remaining.tail)
        result
      }
    })
  }._3

}
