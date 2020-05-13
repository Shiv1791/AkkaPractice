package com.knoldus.akka

import akka.NotUsed
import akka.actor.typed.{ActorSystem, Behavior, Terminated}
import akka.actor.typed.scaladsl.Behaviors

object Main {
  val system: ActorSystem[HelloWorldMain.SayHello] =
    ActorSystem(HelloWorldMain(), "hello")

  def main(args: Array[String]): Unit = {

    system ! HelloWorldMain.SayHello("World")
    system ! HelloWorldMain.SayHello("Akka")
  }

}