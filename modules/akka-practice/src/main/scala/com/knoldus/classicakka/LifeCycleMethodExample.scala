package com.knoldus.classicakka

import akka.actor.{Actor, ActorSystem, Props}

class LifeCycleMethodExample  extends  Actor {

  override def receive: Receive = {
    case msg:String =>
      println(msg+" "+ self.path.name)
      val a = 10/0
      a
  }

  override def preStart: Unit = {
    println("preStart method called ")
  }

  override def postStop(): Unit = {
    println("postStop method called ")
  }

  override def preRestart(reason: Throwable, message: Option[Any]): Unit = {
    println("preRestart method is called")
    println("Reason: "+reason)
  }

  override def postRestart(reason:Throwable){    // Overriding preRestart method
    println("postRestart method is called");
    println("Reason: "+reason)
  }
}


object ActorMain {
  def main(args: Array[String]): Unit = {
    val actorSystem = ActorSystem("ActorSystemLifeCycle")

    val actor = actorSystem.actorOf(Props(classOf[LifeCycleMethodExample]), "LifeCycleMethodExample")
    actor ! "Hello "

    println("Stopping actor")
    actorSystem.stop(actor)
  }
}