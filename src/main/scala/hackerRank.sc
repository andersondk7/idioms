import scala.annotation.tailrec
import scala.util.{Failure, Success, Try}
import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.{Future, Promise}

case class Time(hour: Int, minute: Int, second: Int) {
  def format(v: Int): String = "%02d".format(v)
  override def toString: String = (hour, minute, second) match {
    case (12, 0, 0) => f"${format(0)}:${format(minute)}:${format(second)}"
    case _ => f"${format(0)}:${format(minute)}:${format(second)}"
  }
}
object Time {
  def apply(s: String): Time = {
    val parts = s.split(":")
    println(s"parts: ${parts.mkString(":")}")
    val minute = parts(1).toInt
    val secondAMPM = parts(2)
    val second = parts(2).reverse.drop(2).reverse.toInt
    val hour = if (s.contains('P')) parts(0).toInt + 12 else parts(0).toInt
    new Time(hour, minute, second)
  }
}

val times = Seq(
  "12:00:00AM",
  "11:00:00PM",
  "00:00:00AM",
  "00:00:01AM",
  "00:00:10AM",
  "00:01:00AM",
  "00:10:00AM",
  "01:00:00AM",
  "10:00:00AM",
  "00:00:00PM",
  "00:00:01PM",
  "00:00:10PM",
  "00:01:00PM",
  "00:10:00PM",
  "01:00:00PM",
  "10:00:00PM"

)
val timeStrings = times.map(Time(_).toString)

val candles: Array[Int] = Array(2, 3, 2, 1, 3, 1)
case class CandleCalc(maxHeight: Int, blownCount: Int)
object CandleCalc {
  val empty: CandleCalc = new CandleCalc(0, 0)
  def apply(cc: CandleCalc, height: Int): CandleCalc = height match {
    case newMax if height > cc.maxHeight => new CandleCalc(newMax, 1)
    case isMax if height == cc.maxHeight => cc.copy(blownCount = cc.blownCount + 1)
    case _ => cc
  }
}
import CandleCalc._
val calculation = candles.foldLeft[CandleCalc](empty) ( (calc, height) => CandleCalc(calc, height) )



//val tripSegments: List[(String, String)] = List( ("b", "c"), ("a", "b"), ("d", "e"), ("c", "d"))

// actual a -> b, b -> c, c -> d, d -> e, e -> c , c -> f
// started at a
// ended at f
// really liked location c so went back during the trip

val tripSegments: List[(String, String)] = List( ("b", "c"), ("a", "b"), ("e", "c"), ("d", "e"), ("e", "f"), ("c", "d"))
val startToFinish = tripSegments.toMap
val starts = startToFinish.keySet
val dests = startToFinish.values.toSet
val start = starts.diff(dests)

val finishToStart = startToFinish.map(e => e._2 -> e._1)
val startsReverse = finishToStart.keySet
val destsReverse = finishToStart.values.toSet
val end = startsReverse.diff(destsReverse)

val n = 6
def staircase(n: Int): Seq[String] = {
  (1 to n)
    .map(i => List.fill(-1 * (i-n))('.') ++ List.fill(i)('#'))
    .map(l => l.mkString(""))
}

staircase(n).foreach(println(_))


val init:Array[Int] = Array(123456789, 312345678, 512345678, 212345678, 412345678)
val sorted = init.map(_.toLong).sorted
val result: (Long, Long) = (sorted.take(4).sum, sorted.drop(1).sum)


case class ElementCount(posCount: Int, negCount: Int, zeroCount: Int) {
  def calcPercents(size: Int): Array[Double] = Array(
    posCount.toDouble / size,
    negCount.toDouble / size,
    zeroCount.toDouble / size
  )
}

object ElementCount {
  val empty = ElementCount(0, 0, 0)

  def incPos(ec: ElementCount) = ec.copy(posCount = ec.posCount + 1)

  def incNeg(ec: ElementCount) = ec.copy(negCount = ec.negCount + 1)

  def incZero(ec: ElementCount) = ec.copy(zeroCount = ec.zeroCount + 1)

}
  def plusMinus(arr: Array[Int]) {
    import ElementCount._

    val result = arr.foldLeft[ElementCount](ElementCount.empty)((acc, i) => {
      i match {
        case p if i > 0 => incPos(acc)
        case n if i < 0 => incNeg(acc)
        case _ => incZero(acc)
      }
    })
    result.calcPercents(arr.length).foreach(p => println(s"\n$p\n"))
  }

plusMinus(Array(1,2,-4,-6,0, 8))



val ar: Array[Array[Int]] = Array(
  Array(1,2,3,4),
  Array(1,3,6,2),
  Array(1,8,5,4),
  Array(2,2,3,7)
)
val size = ar.length - 1
var primary:Int = 0
var diag:Int = 0



for (i <- 0 to size){
  for (j <- 0 to size) {
    if (i == j) {
      println(s"primary: $i:$j ${ar(i)(j)}")
      primary = primary + ar(i)(j)
    }
    if ((i+j) == size) {
      println(s"diag: $i:$j ${ar(i)(j)}")
      diag = diag + ar(i)(j)
    }
  }
}
primary
diag


def main(args: Array[String]) {
  val input: String = scala.io.StdIn.readLine()
  val elements: List[String] = input.split(" ").toList
  println(elements)
}

//for (a <- 1 to 10) { println("HelloWorld")}
//
//val l = List(
//  3,
//  -2,
//-4,
//6,
//-5,
//7,
//8,
//0,
//1
//)
//List.fill(2)(3)
//l.flatMap(List.fill(2)(_))
//
//l.filter(i => i < 3)
//
//val odds = l.zipWithIndex.filter(p => p._2 % 2 == 1).map(_._1)
//val before = l
//
//val count = l.length
//val reversed = l.zipWithIndex.sortBy( count - _._2).map(_._1)

//val odd =  l.filter(e => {
//  println(s"$e: ${e%2}")
//  (e%2).abs == 1
//})
//



