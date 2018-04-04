//
// options
//
val o1: Option[Int] = Some(1)
val o2: Option[Int] = Some(2)
val o3: Option[Int] = None
val o4: Option[Int] = Some(4)

val noneList: List[Option[Int]] = List(o1,o2,o3,o4)
val someList: List[Option[Int]] = List(o1,o2,o4)

// ------------------------------
// get those that are defined
// ------------------------------
val od1: List[Int] = noneList.filter(_.isDefined).map(_.get)
od1.length == 3

val od2: List[Int] = noneList.flatten
od2.length == 3

val od3: List[Int] = noneList.collect { case Some(i) => i}
od3.length == 3

// ------------------------------
// get count of those that failed
// ------------------------------
val of1: List[Option[Int]] = noneList.filter(_.isEmpty)
of1.length == 1

val of3: List[Option[Int]] = noneList.collect { case None => None }
of3.length == 1

// ------------------------------
// do something with only the successes
// ------------------------------
def doubleSumOnlySome(list: List[Option[Int]]): Int = list.flatten.fold(0) ((s, i) => { s + (2*i) })
doubleSumOnlySome(noneList) == 14  // 2*1 + 2*2 + 2*4


// define a functions that takes a List[..] and returns an Int
val doWhenAllExist: (List[Option[Int]] => Int)  = (list: List[Option[Int]]) => {
  println(s"calculating doubleSum because all elements exist")
  // the flatten converts from Option[Int] to Int
  // the fold walks through the list, reducing it to some single value
  list.flatten.fold(0) ( (s, i) => {s + (2*i)})
}
val doWhenOnlySomeExist: (List[Option[Int]] => Int)  = (list: List[Option[Int]]) => {
  println(s"defaulting to length of list because some elements don't exist")
  list.length
}

// ------------------------------
// do something if all are defined, something else if some where none
// ------------------------------
if (noneList.contains(None)) doWhenOnlySomeExist(noneList)
else doWhenAllExist(noneList)

if (someList.contains(None)) doWhenOnlySomeExist(someList)
else   doWhenAllExist(someList)


