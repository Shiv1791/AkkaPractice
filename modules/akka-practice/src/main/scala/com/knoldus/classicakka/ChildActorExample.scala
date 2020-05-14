package com.knoldus.classicakka

import akka.actor.{Actor, ActorSystem, Props}


class RootActor extends Actor {
  def receive: Receive = {
    case msg: String =>
      println(msg+" "+self.path.name)
      val childActor = context.actorOf(Props[Child], "Child")
      childActor ! "Hello"
  }
}

class Child extends Actor {
  override def receive: Receive = {
    case msg: String =>
      println(msg+" "+self.path.name)
      val grandChildActor  = context.actorOf(Props[GrandChild], "GrandChild")
      grandChildActor ! "Hi"
  }
}

class GrandChild extends Actor {
  override def receive: Receive = {
    case msg: String =>
      println(msg+" "+ self.path.name)
  }
}

object ChildActorExample {

  def main(args: Array[String]): Unit = {
    val actorSystem = ActorSystem("ActorSystemTest")
    val actor = actorSystem.actorOf(Props[RootActor], "RootActor")
    val actor1 = actorSystem.actorOf(Props[Child], "ChildActor")
    actor1 ! "Hello"
    actor1 ! "Hello"
    actor1 ! "Hello"
    actor1 ! "Hello"
    actor1 ! "ChachaHello"
    actor ! "Hello"

  }
}
