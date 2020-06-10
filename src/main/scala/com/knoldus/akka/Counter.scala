package com.knoldus.akka

import akka.actor.{Actor, ActorSystem, PoisonPill, Props}
import com.knoldus.akka.Counter1.{Dec, Inc}

class Counter1 extends Actor {

var count = 0
  override def receive: Receive = {
    case Inc(x) =>
      count += x
    case Dec(x) =>
      count -= x
  }
}


object Counter1 {
 final case class Inc(x: Int)
 final case class Dec(x: Int)
}

object ActorPath {

  /*def main(args: Array[String]): Unit = {
    val system = ActorSystem("Actor-Paths")
    val Counter11 = system.actorOf(Props[Counter1],"Counter1")

    println(s"Actor reference for Counter11: $Counter11")

    val Counter1Selection1 = system.actorSelection("Counter1")
    println(s"Actor Selection for Counter11: $Counter1Selection1")

    Counter11 ! PoisonPill

    Thread.sleep(100)

    val Counter12 = system.actorOf(Props[Counter1],"Counter1")
    println(s"Actor reference for Counter12: $Counter12")

    val Counter1Selection2 = system.actorSelection("Counter1")
    println(s"Actor Selection for Counter12: $Counter1Selection2")

    system.terminate()

  }*/
}