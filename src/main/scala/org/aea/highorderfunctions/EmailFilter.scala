package org.aea.highorderfunctions

/**
  * Functions that filter emails
  *
  * the main function in this object is the [[EmailFilter.filterEmails(...)]]
  * which takes a [[Seq[Email]] and a filter function and returns emails that should be kept
  *
  * The filter passed in to [[EmailFilter.filterEmails(...)]] are based on
  * the [[org.aea.highorderfunctions.EmailFilter.EmailFilterFunction]], a function that takes
  * an [[Email]] and returns a [[Boolean]] if the email should be kept
  *
  * they typically take a parameter (list of senders, min/max size, etc.) and return a function that
  * takes an [[Email]] and return a [[Boolean]] which will be true if the email is to be kept.
  *
  * see [[EmailFilter.sentByOneOf]] for an example
  *
  * the other functions in this object are support functions for the
  * [[org.aea.highorderfunctions.EmailFilter.EmailFilterFunction]]s
  *
  */
object EmailFilter {
  type EmailFilterFunction = Email => Boolean

  // ***********************************
  // support functions used to filter emails
  // ***********************************

  /**
    * checks the size of an email's text
    * @param check function to compare email's text with a value
    * @param n value to be compared
    * @param email email to be used in the comparison
    * @return return value of check function
    */
  private def sizeConstraint(check: (Int, Int) => Boolean, n: Int, email: Email) = check(email.text.length, n)

  // implementations of the check: (Int, Int) => Boolean function used in sizeConstraint
  private val gt: (Int, Int) => Boolean = _ > _
  private val ge: (Int, Int) => Boolean = _ >= _
  private val lt: (Int, Int) => Boolean = _ < _
  private val le: (Int, Int) => Boolean = _ <= _
  private val eq: (Int, Int) => Boolean = _ == _

  /**
    * create a function that always returns the opposite of the function passed in
    *
    * calls the function, which will return a Boolean
    * then returns the opposite
    *
    * @param function function that takes an A and returns a Boolean
    * @tparam A input to the predicate function
    */
  private def complement[A](function: A => Boolean): A => Boolean = (a: A) => {
    println(s"complement...")
    !function(a)
  }

  /**
    * filter emails
    *
    * @param mails initial emails
    * @param f function that determines if an email should be kept
    * @return sequence of selected emails
    */
  def filterEmails(mails: Seq[Email], f: EmailFilterFunction): Seq[Email] = mails.filter(f)

  // ***********************************
  // building blocks used to create EmailFilterFunctions
  // ***********************************

  /**
    * create a function of type A => Boolean where the result of the function is true
    * if *any* of the functions passed in would return true
    *
    * calls each passed in function until one of them returns true,
    * if none of them return true, then return false
    *
    * @param functions to check
    * @tparam A what gets passed into the function
    * @return either a function A => true if any of the functions return a true, A => false otherwise
    */
  def any[A](functions: (A => Boolean)*): A => Boolean = {
    a => functions.exists(p => p(a)) // calls each predicate until one returns true
  }

  /**
    * create a function of type A => Boolean where the result of the function is true
    * if *none* of the functions passed in would return true
    *
    * calls each passed in function until one of them returns false, if none of them return false, then return true
    *
    * @param functions to check
    * @tparam A what gets passed into the function
    * @return either a function A => true if any of the functions return a true, A => false otherwise
    */
  def none[A](functions: (A => Boolean)*): A => Boolean = complement(any(functions: _*))

  /**
    * returns a function of type A => Boolean if *every* one of the functions passed in returns a Boolean
    *
    * lots of double negatives but the gist of this is:
    * if none of the functions return false, then return true
    *
    * @param functions to check
    * @tparam A what gets passed into the function
    * @return either a function A => true if any of the functions return a true, A => false otherwise
    */
  def every[A](functions: (A => Boolean)*): A => Boolean = none(functions.view.map(complement(_)): _*)


  /**
    * function that takes a [[Set[String]] representing senders and returns a [[EmailFilterFunction]] that returns true if the email was sent by one of these
    *
    * senders is the argument to the function, a [[Set[String]]
    * email => ... is the return value (a function that takes an Email and returns a Boolean) ([[Email]] => [[Boolean]])
    */
  val sentByOneOf: Set[String] => EmailFilterFunction = senders => email => {
    val result: Boolean = senders.contains(email.sender)
    println(s"calling sentByOneOf on ${email.subject} with result: $result")
    result
  }

  /**
    * function that takes a [[Set[String]] representing senders and returns a
    * [[EmailFilterFunction]] that returns true if the email was *not* sent by one of these
    *
    * this will create a new function by applying [[sentByOneOf]] with the [[Set[String]] args
    * and then apply the [[complement]] function to the function returned
    *
    * note that when executed, this new function:
    * 1. calls complement ...
    * 1. calls [[sentByOneOf]]
    */
  val notSentByAnyOf: Set[String] => EmailFilterFunction = sentByOneOf.andThen(g => complement(g))

  /**
    * function that takes an [[Integer]] and return a [[EmailFilterFunction]] that
    * returns true if the email text is at least the minimum size
    *
    * [[EmailFilterFunction]] is the return value (a function that takes an Email and
    * returns a Boolean) ([[Email]] => [[Boolean]])
    */
  val minimumSize: Int => EmailFilterFunction = min => email => sizeConstraint(ge, min, email)

  /**
    * function that takes an [[Integer]] and return a [[EmailFilterFunction]] that returns
    * true if the email text is at least the maximum size
    *
    * [[EmailFilterFunction]] is the return value (a function that takes an Email and returns a Boolean)
    * ([[Email]] => [[Boolean]])
    */
  val maximumSize: Int => EmailFilterFunction = max => email => sizeConstraint(le, max, email)
}
