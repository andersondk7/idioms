var isAsserted = false

// takes a function with no parameters and returns a boolean
def checkByFunction(predicate: () => Boolean): Unit = {
  if (isAsserted && !predicate()) throw new AssertionError() // should check and predicate is false
    // when isAssserted is false, don't bother to check the predicate
}


// awkward construct
println("by function success")
checkByFunction( () => { println("function evaluated"); 9 > 7})

//println("by function failure")
//checkByFunction( () => { println("evaluated"); 5 > 7})
//

def checkByName(predicate: => Boolean): Unit = {
  if (isAsserted && !predicate) throw new AssertionError() // should check and predicate is false
  // when isAssserted is false, don't bother to check the predicate
}

// easier construct
println("by function success")
checkByName({ println("param evaluated"); 9 > 7})

//println("by function failure")
//checkByName({ println("evaluated"); 5 > 7})
//

// easy construct but function is ALWAYS evaluated
def check(predicate: Boolean): Unit = {
  if (isAsserted && !predicate) throw new AssertionError() // should check and predicate is false
  // when isAssserted is false, don't bother to check the predicate
}

println("by check success")
check({ println("param evaluated"); 9 > 7})
