name := "iterators-scala"

version := "0.3"

scalaVersion := "3.3.0"

scalacOptions += "@.scalacOptions.txt"

libraryDependencies += "org.scalatest" %% "scalatest" % "3.2.16" % Test

logBuffered := false

Test / parallelExecution := false

enablePlugins(JavaAppPackaging)
enablePlugins(JmhPlugin)
