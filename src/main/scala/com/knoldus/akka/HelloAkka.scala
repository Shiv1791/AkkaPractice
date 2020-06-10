package com.knoldus.akka

import akka.actor.{Actor, ActorSystem, Props}

//Define Actor Messages
case class WhoToGreet(who: String)

// Define Greeter Actor
class Greeter extends Actor {

  /*
  * it is partial funtion that can take anything and return unit
  * */
  override def receive: Receive = {
    case WhoToGreet(who)=> println(s"Hello $who")
  }
}
object HelloAkka {


/*
  def main(args: Array[String]): Unit = {
    val actorSystem = ActorSystem("Hello-Actor")
    val greeter = actorSystem.actorOf(Props[Greeter], "greeter")

    greeter ! WhoToGreet("Akka")
  }
*/

}
