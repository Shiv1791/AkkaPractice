import BuildSettings._
import Dependencies._
import sbt._

name := "AkkaPractice"

version := "0.1"
scalaVersion := "2.12.2"

val akkaVersion = "2.6.5"
libraryDependencies ++= Seq("com.typesafe.akka" %% "akka-actor" % "2.6.5", "org.slf4j" % "slf4j-api" % "1.7.25",
  "com.typesafe.akka" %% "akka-remote" % "2.6.5", "com.gu" %% "scanamo" % "1.0.0-M8")



// Enable experimental cached SBT/Ivy library resolution to speed up compile
updateOptions := updateOptions.value.withCachedResolution(true)

val commonDeps = Seq(
  libraryDependencies ++= compiledDependencies(typesafeConfig ) ++
    testDependencies(scalaTest, mockitoCore, akkaTestKit )
)

lazy val akka_practice = BaseProject("akka-practice")
  .settings(commonDeps)
  .settings(libraryDependencies ++= compiledDependencies(akkaActor1, akkaStream))
  .configs(IntegrationTest.extend(Test))
