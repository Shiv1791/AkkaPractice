import BuildSettings._
import Dependencies._
import sbt._

name := "AkkaPractice"

version := "0.1"

scalaVersion := "2.13.2"

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
