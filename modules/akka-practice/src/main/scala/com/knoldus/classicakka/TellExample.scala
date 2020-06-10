package com.knoldus.classicakka

import akka.actor.{Actor, ActorSystem, Props}
import akka.util.Timeout
import akka.pattern.ask

import scala.concurrent.Await
import scala.concurrent.duration._

class TellExample extends Actor {

  override def receive: Receive = {
    case message: String =>
      println("Message received: " + message + " from " + self.path.name)
      println("sender: "+sender())
      Thread.sleep(5000)
      println("Replaying");
      val senderName = sender();
      senderName ! "Hello, I got your message.";      // Replying message
/*
      val child = context.actorOf(Props[Actor2], "ChildActor");
      child ! "Hello"
*/
  }
}
/*

class Actor2 extends Actor {
  override def receive: Receive = {
    case message:String => println("Message received in Actor2: "+message+ " from - "+ self.path.name);
      println("Sender: "+sender())
  }
}
*/

object TellExample {
  def main(args: Array[String]): Unit = {
    val actorSystem = ActorSystem("ActorSystem")
    val actor =
      actorSystem.actorOf(Props(classOf[TellExample]), "RootActor")
/*
    actor ! " Hello "
    actor.tell(" Hello ", null)
*/

    implicit val timeout: Timeout = Timeout(6 seconds)
    val future = actor ? "Hello"
    val result = Await.ready(future, timeout.duration)
    println("Finally Message received: " + result)

  }
}