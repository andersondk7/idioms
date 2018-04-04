package org.aea.highorderfunctions

/**
  * Collection of transformation functions
  *
  * These functions take an [[Email]] and transform it into another [[Email]]
  * The [[EmailTransformation]] is the definition of the transform
  */
object EmailTransformation {
  type EmailTransformFunction = Email => Email
  /**
    * adds _No subject_ to an email without a subject
    */
  val addMissingSubject: EmailTransformFunction = email =>
    if (email.subject.isEmpty) email.copy(subject = "No subject")
    else email

  /**
    * returns a [[EmailTransformation]] function that replaces sender with the supplied senderName
    */
  val addSender: String => EmailTransformFunction = senderName => email => email.copy(sender = senderName)

  /**
    * returns a [[EmailTransformation]] function that replaces subject with the supplied updatedSubject
    */
  val addSubject: String => EmailTransformFunction = updatedSubject => email => email.copy(subject = updatedSubject)

  /**
    * combined multiple [[EmailTransformation]] functions into a single [[EmailTransformation]] that executes each supplied function in order
    * @param transforms transforms to be applied
    */
  def buildPipeline( transforms: Seq[EmailTransformFunction]): EmailTransformFunction = Function.chain(transforms)
}
