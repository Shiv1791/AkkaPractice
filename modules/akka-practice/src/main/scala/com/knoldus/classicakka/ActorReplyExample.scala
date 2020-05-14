package com.knoldus.classicakka

import akka.actor.{Actor, ActorSystem, Props}

class ActorReplyExample extends Actor {

  override def receive: Receive = {
    case message: String =>
      println("Message in Root: "+message+" "+self.path.name)
      val child = context.actorOf(Props[ActorChildReplyExample], "ActorChild")
      child! "Hello child"
      child! message // messege forward to child actor
  }
}

class ActorChildReplyExample extends Actor {
  override def receive: Receive = {
    case message:String => println("Message received from "+sender.path.name+" massage: "+message)
      println("Replying to "+sender().path.name)
      sender()! "I got you message"
    case _ => println("Unknown message")
      sender()! "I got you message"
  }
}

object ActorReplyExample{
  def main(args:Array[String]){
    val actorSystem = ActorSystem("ActorSystem");
    val actor = actorSystem.actorOf(Props[ActorReplyExample], "RootActor");
    actor ! "Hello"
  }
}

