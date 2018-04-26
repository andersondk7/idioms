package org.aea.formatting

case class Time(hour: Int, minute: Int, second: Int) {
  def format(v: Int): String = "%02d".format(v)
  override def toString: String = f"${format(hour)}:${format(minute)}:${format(second)}"
}

object Time {
  def apply(input: String): Time = {
    val parts = input.split(":")
    val hour = parts(0).toInt
    val minute = parts(1).toInt
    val secondAMPM = parts(2)
    val second = parts(2).reverse.drop(2).reverse.toInt
    val am = input.contains('A')
    (hour, minute, second, am) match {
      case (12, m, s, true) => Time(0, m, s) // 12 am is 0 military
      case (12, m, s, false) => Time(12, m, s) // 12 pm is 12 military
      case (h, m, s, false) => Time(h+12, m, s) // 1am to 11pm is 13 to 23 military
      case (h, m, s, true) => Time(h, m, s) // 1 am to 11 am is 1 to 11 military
    }
  }
}
