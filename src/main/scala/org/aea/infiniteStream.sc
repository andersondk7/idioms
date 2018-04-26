println(s"hello there")


val list = 1 :: 2 :: 3 :: Nil
val stream = 1 #:: 2 #:: 3#:: Stream.empty

val mList = list.map(_*2)
val mStream = stream.map(_*2)

def infiniteStream(start: Int): Stream[Int] = Stream.cons(start, infiniteStream(start+1))


infiniteStream(1).take(10) // does not calculate until we do something with the elements
infiniteStream(1).take(10).print // does not calculate until we do something with the elements

