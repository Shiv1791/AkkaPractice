package com.knoldus.classicakka

import akka.actor
import akka.actor.{Actor, ActorLogging, ActorSystem, Props}
import com.knoldus.classicakka.MyActor.GoodBye

object MyActor {

  case class Greeting(from: String)
  case object GoodBye
}


class MyActor extends Actor with ActorLogging {
  import MyActor._

  override def receive: Receive = {
    case Greeting(greeter) => println(s"I was greeted by $greeter")
    case GoodBye => println("Someone said good bye to me")
  }
}


class FirstActor extends Actor {
  val child: actor.ActorRef = context.actorOf(Props[MyActor], "myChild")
  override def receive: Receive = {
    case x =>
      println(s"yaha bho aaya  $x")
      sender() ! x
  }

}

class Argument(val value: String) extends AnyVal
class ValueClassActor(arg: Argument) extends Actor {
  override def receive: Receive = {
    case _ => println("I don't give a shit")
  }
}

/*  self reference to the ActorRef of the actor
    sender reference sender Actor of the last received message, typically used as described in Actor.Reply
    supervisorStrategy user overridable definition the strategy to use for supervising child actors
 */

object Main {

  def main(args: Array[String]): Unit = {
    val actorSystem = ActorSystem("ActorSystem")
    val actor = actorSystem.actorOf(Props(new MyActor()), "MyActor")

    def props1(arg: Argument)  =  Props(classOf[ValueClassActor], arg)
    def props2(arg: Argument)  =  Props(classOf[ValueClassActor], arg.value)
    def props3(arg: Argument)  =  Props(new ValueClassActor(arg))

    actor ! MyActor.Greeting("hello")
    actor ! GoodBye

    props2(new Argument("Raj"))
    props3(new Argument("Shiv"))
    //props1(new Argument("Hello")) // Give exception java.lang.IllegalArgumentException: no matching constructor found
  }
}