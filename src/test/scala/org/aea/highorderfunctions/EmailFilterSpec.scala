package org.aea.highorderfunctions

import org.scalatest.{FunSpec, Matchers}

class EmailFilterSpec extends FunSpec with Matchers {
  import EmailFilter._
  val e1 = Email("s1", "text: 1234", "bob", "mary")
  val e2 = Email("s2", "text: 123456789", "bob", "susan")
  val e3 = Email("s3", "text: 1234", "bill", "mary")
  val e4 = Email("s4", "text: 123456789", "bill", "susan")

  val emails = Seq(e1, e2, e3, e4)
  describe("EmailFilter") {
    it("should only include from specific sender") {
      println("\nshould only include from specific sender")
      val fromBob = EmailFilter.filterEmails(emails, sentByOneOf(Set("bob")))
      fromBob.length shouldBe 2
      fromBob.map(_.subject).contains(e1.subject) shouldBe true
      fromBob.map(_.subject).contains(e2.subject) shouldBe true
    }
    it("should exclude from specific sender") {
      println("\nshould exclude from specific sender")
      val notFromBob = EmailFilter.filterEmails(emails, notSentByAnyOf(Set("bob")))
      notFromBob.length shouldBe 2
      notFromBob.map(_.subject).contains(e3.subject) shouldBe true
      notFromBob.map(_.subject).contains(e4.subject) shouldBe true
    }
    it("should EmailFilter on text size") {
      println("\nshould EmailFilter on text size")
      val small = EmailFilter.filterEmails(emails, maximumSize(11))
      small.length shouldBe 2
      small.map(_.subject).contains(e1.subject) shouldBe true
      small.map(_.subject).contains(e3.subject) shouldBe true
      val large = EmailFilter.filterEmails(emails, minimumSize(11))
      large.length shouldBe 2
      large.map(_.subject).contains(e2.subject) shouldBe true
      large.map(_.subject).contains(e4.subject) shouldBe true
    }
    it("should filter if any filter matches") {
      println("\nshould filter if any filter matches")
      val anyFilter: EmailFilterFunction = any(
        sentByOneOf(Set("bob")),
        maximumSize(11)
      )
      val anyMatch = EmailFilter.filterEmails(emails, anyFilter)
      anyMatch.length shouldBe 3
      anyMatch.map(_.subject).contains(e1.subject) shouldBe true
      anyMatch.map(_.subject).contains(e2.subject) shouldBe true
      anyMatch.map(_.subject).contains(e3.subject) shouldBe true
    }
    it("should filter if no filter matches") {
      println("\nshould filter if no filter matches")
      val noneFilter: EmailFilterFunction = none(
        sentByOneOf(Set("bob")),
        maximumSize(11)
      )
      val noMatch = EmailFilter.filterEmails(emails, noneFilter)
      noMatch.length shouldBe 1
      noMatch.map(_.subject).contains(e4.subject) shouldBe true
    }
  }
}
