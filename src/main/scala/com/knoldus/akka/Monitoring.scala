package com.knoldus.akka

import akka.actor.{Actor, ActorRef, ActorSystem, OneForOneStrategy, Props, Terminated}

class Ares(athena: ActorRef) extends Actor {

  override def preStart(): Unit = {
    context.watch(athena)
  }

  override def postStop(): Unit = {
    println(s"Ares Stops...")
  }

  override def receive: Receive = {
    case Terminated =>
      context.stop(self)
  }
}

object Ares {
  def props(athena: ActorRef) = Props(new Ares(athena))
}

class Athena extends Actor {

  override def receive: Receive = {
    case message =>
      println(s"Athena received $message")
      context.stop(self)
  }
}

object Monitoring {
/*
  def main(args: Array[String]): Unit = {
    val system = ActorSystem("monitoring")

    val athena = system.actorOf(Props[Athena], "athena")
    val ares = system.actorOf(Ares.props(athena), "ares")
    athena! "Hi"
    ares ! "Hi"

    system.terminate()
  }*/

}
