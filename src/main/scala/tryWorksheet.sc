import scala.concurrent.{Await, Future}
import scala.util.{Failure, Success, Try}
import scala.concurrent.duration._

// ------------------------------
// trys
// ------------------------------
val t1: Try[Int] = Success(1)
val t2: Try[Int] = Success(2)
val t3: Try[Int] = Failure(new Exception())
val t4: Try[Int] = Success(4)
val t5: Try[Int] = Success(5)

val fList: List[Try[Int]] = List(t1,t2,t3,t4)
val sList: List[Try[Int]] = List(t1,t2,t3,t5)

// ------------------------------
// get those that were successful
// ------------------------------
val ts1: List[Int] = fList.filter(_.isSuccess).map(_.get)
ts1.length == 3

val ts2: List[Int] = fList.flatMap(_.toOption)
ts2.length == 3

val ts3: List[Int] = fList.collect{ case Success(i) => i }
ts3.length == 3

val fs1: List[Try[Int]] =  fList.filter(_.isFailure)
fs1.length == 1

val fs2: List[Throwable] = fList.collect{ case Failure(t) => t}
fs2.length == 1

// ------------------------------
// do something only when they all succeed,
// post common failure if any failed
// ------------------------------

// the List[Int] is all the successes, the Option[Throwable] is the first error
fList.foldLeft( (List[Int](), Option[Throwable]) ) ( (r, e) => {
  val (ints, error) = r
  e match {
    case Success(i) => i :: ints
    case Failure(t) => if (error.isEmpty) error = t
  }
  r
})
//
// futures
//
//val duration = 60.millisecond
//val f1: Future[Try[Int]] = Future {Success{Thread.sleep(30); 1}}
//val f2: Future[Try[Int]] = Future {Success{Thread.sleep(20); 2}}
//val f3: Future[Try[Int]] = Future {Success{Thread.sleep(10); 3}}
//val f4: Future[Try[Int]] = Future {Success{Thread.sleep(40); 4}}
//val f5: Future[Try[Int]] = Future {Failure{new Exception()}}
//
//val fList: List[Future[Try[Int]]] = List(f1,f2,f3,f4)
//
//val ftValues: Future[List[Try[Int]]] = for {
//  a <- f1
//  b <- f2
//  f <- f5
//  d <- f4
//} yield List(a, b, f, d)
//
//val fValues: List[Try[Int]] = Await.result(ftValues, duration)
//
//val fValues2: List[Int] = fValues.flatMap(_.toOption)
//fValues2.length == 3
