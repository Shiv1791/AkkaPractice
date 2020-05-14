/*
package com.knoldus.akka

import akka.actor.typed.{ActorSystem, Behavior}
import akka.actor.typed.scaladsl.Behaviors
import com.knoldus.classicakka.SomePractice

object HelloWorldMain {

  final case class SayHello(name: String)

  def apply(): Behavior[SayHello] =
    Behaviors.setup { context =>
      val greeter = context.spawn(SomePractice(), "greeter")

      Behaviors.receiveMessage { message =>
        val replyTo = context.spawn(HelloWorldBot(max = 3), message.name)
        greeter ! SomePractice.Greet(message.name, replyTo)
        Behaviors.same
      }
    }

  def main(args: Array[String]): Unit = {
    val system: ActorSystem[HelloWorldMain.SayHello] =
      ActorSystem(HelloWorldMain(), "hello")

    system ! HelloWorldMain.SayHello("World")
    system ! HelloWorldMain.SayHello("Akka")
  }
}*/
