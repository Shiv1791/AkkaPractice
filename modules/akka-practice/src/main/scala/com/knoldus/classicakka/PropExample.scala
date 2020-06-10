package com.knoldus.classicakka

import akka.actor.{Actor, ActorSystem, Props}

class PropExample extends Actor {
  def receive: Receive = {
    case msg: String =>
      println(s"${msg} from ${self.path.name}")
  }
}

object PropExample {
  def main(args: Array[String]): Unit = {
    val actorSystem = ActorSystem("testActorSystem")

    val prop1 = Props[PropExample]
    val actor1 = actorSystem.actorOf(prop1)
    val actor2 = actorSystem.actorOf(Props[PropExample], "Actor2")
    val actor3 = actorSystem.actorOf(Props(classOf[PropExample]), "Actor3")
    val actor4 = actorSystem.actorOf(Props[PropExample], name = "Actor4")
    val actor5 = actorSystem.actorOf(Props( new PropExample()), name = "Actor5")
    actor1 ! "Hello bro"
    actor2 ! "Hello bro"
    actor3 ! "Hello bro"
    actor4 ! "Hello bro"
    actor5 ! "Hello bro"
  }
}