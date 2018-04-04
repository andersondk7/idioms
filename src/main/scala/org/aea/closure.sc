// function
def sum(x: Int, y: Int): Int = {x + y}

// partial function (specifies some of the parameters, but not all
val inc = sum(1, _: Int)

inc(4)

// fuction as val
val s: (Int, Int) => Int = (x: Int, y: Int) => {x + y}

s(1,5)


List(1,2,3,4).reduce(s)

// closure - a function that refers to a value not defined in the function
val step = 5

// an 'open term' because it is not closed over the free variable 'step'
val ss = (x: Int) => {x + step}


ss(3)

