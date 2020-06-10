name := "akkaonline"

version := "0.1"

scalaVersion := "2.12.2"

val akkaVersion = "2.6.5"
libraryDependencies ++= Seq("com.typesafe.akka" %% "akka-actor" % "2.6.5", "org.slf4j" % "slf4j-api" % "1.7.25",
  "com.typesafe.akka" %% "akka-remote" % "2.6.5", "com.gu" %% "scanamo" % "1.0.0-M8")