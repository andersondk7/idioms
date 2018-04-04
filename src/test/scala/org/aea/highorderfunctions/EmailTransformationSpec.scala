package org.aea.highorderfunctions

import org.scalatest.{FunSpec, Matchers}

/**
  */
class EmailTransformationSpec extends FunSpec with Matchers {
  import EmailTransformation._

  describe("transform") {
    val e1 = Email("s1", "text: 1234", "bob", "mary")
    val e2 = Email("s2", "text: 123456789", "bob", "susan")
    val e3 = Email("s3", "text: 1234", "bill", "mary")
    val e4 = Email("s4", "text: 123456789", "bill", "susan")

    val emails = Seq(e1, e2, e3, e4)
    val totalEmails = emails.length
    it("should replace sender and subject") {
      val testSender = "tester"
      val testSubject = "test only"
      val pipeline = buildPipeline(
        Seq(
          addSender(testSender),
          addSubject(testSubject)
        )
      )
      val results = emails.map(e => pipeline(e))
      println(s"results: $results")
      results.count(_.subject == testSubject) shouldBe totalEmails
      results.count(_.sender == testSender) shouldBe totalEmails

    }
  }

}
