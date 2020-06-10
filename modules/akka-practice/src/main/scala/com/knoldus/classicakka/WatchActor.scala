package com.knoldus.classicakka

import akka.actor.{Actor, Props, Terminated}

class WatchActor extends Actor {
  var child =  context.actorOf(Props.empty, "Child")
  context.watch(child)
  context.actorSelection(".")

  var lastSender =  context.system.deadLetters

  override def receive: Receive = {
    case "Kill" =>
      context.stop(child)
      lastSender = sender()
    case Terminated(_) => lastSender ! "finished"
  }

  override def preStart(): Unit = {
    child = context.actorOf(Props[MyActor], "child")
  }

}
