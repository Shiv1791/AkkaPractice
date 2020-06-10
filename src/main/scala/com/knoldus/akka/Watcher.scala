package com.knoldus.akka

import akka.actor.{Actor, ActorIdentity, ActorRef, ActorSelection, Identify}

class Watcher extends Actor {
  var counterRef: ActorRef = _
  val selection: ActorSelection = context.actorSelection("user/counter")

  selection ! Identify(None)

  def receive: Receive = {
    case ActorIdentity(_, Some(actorRef)) =>
      println(s"Actor reference for the counter is  $actorRef")
    case ActorIdentity(_, None) =>
      println("Actor selection for actor doesn't live: ( ) ")
  }
}

/*
object Watch extends App {

}*/
