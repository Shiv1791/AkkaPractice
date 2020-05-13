package com.knoldus.akka

import akka.actor.typed.Behavior
import akka.actor.typed.scaladsl.Behaviors
import com.knoldus.classicakka.SomePractice


object HelloWorldBot {

  def apply(max: Int): Behavior[SomePractice.Greeted] = bot(0, max)

  def bot(greetingCount: Int, max: Int): Behavior[SomePractice.Greeted] = {
    Behaviors.receive{ (context, msg) =>
      val n = greetingCount + 1
      context.log.info(msg.whom)
      if(n == max)
        Behaviors.stopped
      else{
        msg.from ! SomePractice.Greet(msg.whom, context.self)
        bot(n, max)
      }
    }
  }
  
}
