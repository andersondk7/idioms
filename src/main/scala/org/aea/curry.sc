def curriedSum(x: Int)(y: Int) = x + y

def plus2(y: Int) = curriedSum(2)(y)

val p2 = curriedSum(2)_

curriedSum(4)(6)
plus2(4)

p2(3)

