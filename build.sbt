name := "iterators-scala"

version := "0.3"

scalaVersion := "3.2.1"

scalacOptions ++= Seq("-deprecation", "-feature", "-unchecked", "-Yexplicit-nulls", "-Ysafe-init", "-language:strictEquality")

coverageEnabled := true

libraryDependencies ++= Seq(
  "org.scalatest"     %% "scalatest"  % "3.2.14"  % Test
)

logBuffered := false

Test / parallelExecution := false

enablePlugins(JavaAppPackaging)
enablePlugins(JmhPlugin)
