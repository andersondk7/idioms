import scala.language.implicitConversions
/**
  */
class Rational(n: Int, d: Int = 1) {
  require(d != 0)
  private val g = gcd(n.abs, d.abs)
  val numer: Int = n /g
  val denom: Int = d / g

  // def this(n: Int) = this(n, 1) // replaced with default argument

  def + (that: Rational): Rational = new Rational(
    (numer * that.denom) + (that.numer * denom) // convert numerator to new denominator
    , denom * that.denom // create new denominator that matches both rationals
  )

  def + (i: Int): Rational = new Rational( numer+i * denom, denom)

  def - (that: Rational): Rational = new Rational(
    (numer * that.denom) - (that.numer * denom) // convert numerator to new denominator
    , denom * that.denom // create new denominator that matches both rationals
  )

  def - (i: Int): Rational = new Rational( numer-i * denom, denom)

  def * (that:Rational): Rational = new Rational(numer * that.numer, denom * that.denom)

  def * (i: Int): Rational = new Rational( numer*i, denom)

  def / (that: Rational): Rational = new Rational(numer* that.denom, denom * that.numer)

  def / (i: Int): Rational = new Rational( numer, denom * i)

  override def toString = s"$numer/$denom"

  private def gcd(a: Int, b: Int): Int =
    if (b == 0) a
    else gcd(b, a % b)  // recursively call again with new set of numbers
}

//object Rational {
  implicit def intToRational(i: Int): Rational = new Rational(i)
//}

val r1 = new Rational(6, 8)
val r2 = new Rational(1, 8)
val r3 = new Rational(3)

r1 + r2
r1 - r2
r3 - r1 - 2

r1 * 3



