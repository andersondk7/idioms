name          := "idioms"
organization  := "org.aea"
version       := "0.0.1"
scalaVersion  := "2.12.1"
scalacOptions := Seq("-unchecked", "-feature", "-deprecation", "-encoding", "utf8")


libraryDependencies += "org.scalatest"     %% "scalatest"       % "3.0.0"       % "test"

lazy val idioms = project.in(file("."))


