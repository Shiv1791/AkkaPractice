import sbt.Keys._
import sbt.{Def, _}

object BuildSettings {

  val projectSettings: Seq[Def.Setting[_]] = Defaults.coreDefaultSettings ++
    Seq(
      organization in ThisBuild := "com.knoldus",
      scalaVersion in ThisBuild := "2.12.6",
      version := "0.0.5",
      fork in Test := true,
      parallelExecution in Test := true,
      concurrentRestrictions in Global += Tags.limit(Tags.Test, 2),
      resolvers += "Sonatype OSS Snapshots" at "https://oss.sonatype.org/content/repositories/snapshots",
      resolvers += "Sonatype OSS Releases" at "https://oss.sonatype.org/content/repositories/releases/",
      resolvers += "bintray algd" at "http://dl.bintray.com/content/algd/maven"
    )

  val sCoverageSettings = Seq(
    parallelExecution in ThisBuild := false,
    /*coverageMinimum := 89,
    coverageFailOnMinimum := true,
    coverageExcludedPackages := "com.knoldus.models.*;com.knoldus.pimp.*;com.knoldus.util.*;com.knoldus.app.*;" +
      "com.knoldus.service.GameEngineService;com.knoldus.service.ConsumerService;com.knoldus.handler.CORSHandler;"+
      "com.knoldus.infrastructure.kafka.KafkaStringConsumer;"*/
  )

 /* lazy val sbtAssemblySettings = Seq(
    assemblyMergeStrategy in assembly := {
      case PathList("org","aopalliance", xs @ _*)                                                 => MergeStrategy.last
      case PathList("javax", "inject", xs @ _*)                                                   => MergeStrategy.last
      case PathList("javax", "servlet", xs @ _*)                                                  => MergeStrategy.last
      case PathList("javax", "activation", xs @ _*)                                               => MergeStrategy.last
      case PathList("org", "apache", xs @ _*)                                                     => MergeStrategy.last
      case PathList("com", "google", xs @ _*)                                                     => MergeStrategy.last
      case PathList("META-INF", "services", "com.google.auto.value.extension.AutoValueExtension") => MergeStrategy.first
      case PathList("META-INF", "services", "io.netty.versions.properties")                       => MergeStrategy.last
      case PathList("META-INF", "services", "io.grpc.ManagedChannelProvider")                     => MergeStrategy.last
      case PathList("META-INF", "services", "io.grpc.ServerProvider")                             => MergeStrategy.last
      case PathList("META-INF", "services", "io.grpc.NameResolverProvider")                       => MergeStrategy.last
      case PathList("META-INF", "services", "io.grpc.LoadBalancerProvider")                       => MergeStrategy.last
      case PathList("META-INF", "native", "libnetty_tcnative.so")                                 => MergeStrategy.first
      case PathList("META-INF", xs @ _*) if xs.contains("com.google")                             => MergeStrategy.first
      case PathList("META-INF", xs @ _*) if xs.contains("netty")                                  => MergeStrategy.last
      case PathList("META-INF", xs @ _*)                                                          => MergeStrategy.discard
      case PathList("reference.conf")                                                             => MergeStrategy.concat
      case "about.html"                                                                           => MergeStrategy.rename
      case "plugin.properties"                                                                    => MergeStrategy.last
      case "log4j.properties"                                                                     => MergeStrategy.last
      case "application.conf"                                                                     => MergeStrategy.concat
      case "logback.xml"                                                                          => MergeStrategy.first
      case x =>
        val oldStrategy = (assemblyMergeStrategy in assembly).value
        oldStrategy(x)
    }
  )*/

  //scalastyle:off
  def BaseProject(name: String): Project =
    Project(name, file(s"modules/$name"))
      .settings(projectSettings: _*)
      .settings(sCoverageSettings: _*)
      //.settings(sbtAssemblySettings: _*)
      .settings(Defaults.itSettings)
  //scalastyle:on

  lazy val codeSquad = taskKey[Unit]("CodeSquad script running.....") := {
    import sys.process._
    "./updateCodesquad.sh" !
  }

}
