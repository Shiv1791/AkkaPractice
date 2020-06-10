import sbt._

object Dependencies {

  def compiledDependencies(deps: ModuleID*): Seq[ModuleID] = deps.map(_ % Compile)

  def testDependencies(deps: ModuleID*): Seq[ModuleID] = deps.map(_ % Test)

  def itDependencies(deps: ModuleID*): Seq[ModuleID] = deps.map(_ % "it")

  def providedDependencies(deps: ModuleID*): Seq[ModuleID] = deps.map(_ % Provided)



  val akkaVersion = "2.6.5"
  val mockitoCoreVersion = "2.23.0"
  val scalaTestVersion = "3.0.5"


  /**
   * Compile libraries
   */

  val typesafeConfig = "com.typesafe" % "config" % "1.3.3"

  val akkaStream = "com.typesafe.akka" %% "akka-stream" % akkaVersion

  val akkaActor1 = "com.typesafe.akka" %% "akka-actor-typed" % akkaVersion
  /**
   * Test libraries
   */

  val scalaTest = "org.scalatest" %% "scalatest" % scalaTestVersion

  val mockitoCore = "org.mockito"  % "mockito-core" % mockitoCoreVersion

  val akkaTestKit = "com.typesafe.akka" %% "akka-testkit" % akkaVersion
}
