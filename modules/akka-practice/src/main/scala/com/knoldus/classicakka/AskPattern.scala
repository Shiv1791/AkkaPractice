package com.knoldus.classicakka
import akka.actor.{Actor, ActorSystem, Props}
import akka.actor.typed.delivery.internal.ProducerControllerImpl.Request
import akka.pattern.{ask, pipe}
import akka.util.Timeout

import scala.concurrent.ExecutionContext
import scala.concurrent.duration._

object Pattern {
  final case class AskPattern(x: Int, s: String, d: Double)

  case object AskPattern

implicit val timeout = Timeout(5 seconds)


  class ActorA extends Actor {

    override def receive: Receive = {
      case x:Int => x
    }
  }
  class ActorB extends Actor {

    override def receive: Receive = {
      case x:String => x
    }
  }
  class ActorC extends Actor {

    override def receive: Receive = {
      case x:Double => x
    }
  }

  val actorSystem = ActorSystem("System")
  val actorA = actorSystem.actorOf(Props(new ActorA), "ActorA")
  val actorB = actorSystem.actorOf(Props(new ActorB), "ActorB")
  val actorC = actorSystem.actorOf(Props(new ActorC), "ActorC")
  val actorD = actorSystem.actorOf(Props(new ActorC), "ActorD")

  implicit val executor = ExecutionContext.Implicits.global


  val f = for{
    x<- ask(actorA, AskPattern).mapTo[Int]
    s <- actorB.ask(Request).mapTo[String]
    d <- (actorC ? Request).mapTo[Double]

  } yield AskPattern(x,s,d)


  f.pipeTo(actorD)
  pipe(f) to actorD

}

