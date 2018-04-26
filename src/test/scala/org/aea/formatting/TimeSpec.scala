package org.aea.formatting

import org.scalatest.{FunSpec, Matchers}

class TimeSpec extends FunSpec with Matchers {

  case class TestCase(input: String, h: Int, m: Int, s: Int) {
    def run = {
      val t = Time(input)
      try {
        t.hour shouldBe h
        t.minute shouldBe m
        t.second shouldBe s
      } catch {
        case e: Exception => {
          printf(s"$input failed, $e")
          fail(s"msg: $input failed, because $e\n\n")
        }
      }
    }
  }

  describe("time") {
    val testCases = Seq(
      TestCase("0:00:00AM", 0, 0, 0),
      TestCase("0:00:01AM", 0, 0, 1),
      TestCase("0:00:10AM", 0, 0, 10),
      TestCase("0:01:00AM", 0, 1, 0),
      TestCase("0:10:00AM", 0, 10, 0),
      TestCase("1:00:00AM", 1, 0, 0),
      TestCase("10:00:00AM", 10, 0, 0),

      TestCase("00:00:00AM", 0, 0, 0),
      TestCase("00:00:01AM", 0, 0, 1),
      TestCase("00:00:10AM", 0, 0, 10),
      TestCase("00:01:00AM", 0, 1, 0),
      TestCase("00:10:00AM", 0, 10, 0),
      TestCase("01:00:00AM", 1, 0, 0),
      TestCase("10:00:00AM", 10, 0, 0),

      TestCase("12:00:00AM", 0, 0, 0),
      TestCase("12:00:01AM", 0, 0, 1),

      TestCase("12:00:00PM", 12, 0, 0),
      TestCase("12:00:01PM", 12, 0, 1),
      TestCase("12:00:10PM", 12, 0, 10),
      TestCase("12:01:00PM", 12, 1, 0),
      TestCase("12:10:00PM", 12, 10, 0),

      TestCase("12:00:00PM", 12, 0, 0),
      TestCase("12:00:01PM", 12, 0, 1),
      TestCase("12:00:10PM", 12, 0, 10),
      TestCase("12:01:00PM", 12, 1, 0),
      TestCase("12:10:00PM", 12, 10, 0),
      TestCase("01:00:00PM", 13, 0, 0),
      TestCase("10:00:00PM", 22, 0, 0),
      TestCase("01:00:00PM", 13, 0, 0),

      TestCase("12:00:00AM", 0, 0, 0),
      TestCase("12:00:00PM", 12, 0, 0)
    )
    it ("should run times") {
      testCases.foreach(_.run)
    }
    it ("should handle corner case") {
      TestCase("01:00:00PM", 13, 0, 0).run
    }


  }
}
