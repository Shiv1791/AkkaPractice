package com.knoldus.akka

import akka.actor.typed.scaladsl.Behaviors
import akka.actor.typed.{ActorRef, Behavior}

object SomePractice {

  final case class Greet(whom: String, replyTo: ActorRef[Greeted])
  final case class Greeted(whom: String, from: ActorRef[Greet])


  def apply(): Behavior[Greet] = Behaviors.receive { (context, msg)=>
    context.log.info(msg.whom)
    msg.replyTo ! Greeted(msg.whom, context.self)
    Behaviors.same
  }

}
