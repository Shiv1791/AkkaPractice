package com.knoldus.classicakka

import akka.actor.{Actor, ActorIdentity, ActorRef, Identify, Terminated}

class Follower extends Actor {

  val identifyId =  1
  context.actorSelection("user/another") ! Identify(identifyId)
  override def receive: Receive = {
    case ActorIdentity(_, Some(ref)) =>
      context.watch(ref)
      context.become(active(ref))
    case ActorIdentity(_, None) =>
      context.stop(self)
  }

  def active(another: ActorRef): Actor.Receive = {
    case Terminated(another) => context.stop(self)
  }

}
