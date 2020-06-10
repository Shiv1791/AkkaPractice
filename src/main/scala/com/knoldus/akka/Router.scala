package com.knoldus.akka

import akka.actor.AbstractActor.Receive
import akka.actor.{Actor, ActorRef, ActorSystem, Props}
import com.knoldus.akka.Worker.Work

class Worker extends Actor {

  override def receive: Receive = {
    case message: Work =>
      println(s" I received work Message and my actorRef: ${self}")
  }
}

object Worker {
  case class Work(message: String)
  def props: Props = Props[Worker]
}

class Router extends Actor {
  var routees: List[ActorRef] = _

  override def preStart(): Unit = {
    routees = List.fill(5){
      context.actorOf(Props[Worker])
    }
  }

  override def receive: Receive = {
    case msg: Work =>
      println("I am a router and I received a message ....")
      routees(util.Random.nextInt(routees.size)) forward (msg)
  }
}

class RouterPool extends Actor {

  var routees: List[ActorRef] = _

  override def preStart(): Unit = {
    routees = List.fill(5){
      context.actorOf(Props[Worker])
    }
  }

  override def receive: Receive = {
    case msg: Work =>
      println("I am a router pool and I received a message ....")
      routees(util.Random.nextInt(routees.size)) forward (msg)
  }
}

class RouterGroup(routees: List[String]) extends Actor {

  override def receive: Receive = {
    case msg: Work =>
      println("I am a router Group and I received a message ....")
      context.actorSelection(routees(util.Random.nextInt(routees.size))) forward msg
  }

  def props = Props[RouterGroup]
}

object Router {

  /*def main(args: Array[String]): Unit = {
    val system = ActorSystem("router")
    val router = system.actorOf(Props[Router])
/*
    router ! Work()
    router ! Work()
    router ! Work()
    router ! Work()
    router ! Work()
    */
    system.actorOf(Props[Worker], "w1")
    system.actorOf(Props[Worker], "w2")
    system.actorOf(Props[Worker], "w3")
    system.actorOf(Props[Worker], "w4")
    system.actorOf(Props[Worker], "w5")

    val workers = List(
      "/user/w1",
      "/user/w2",
      "/user/w3",
      "/user/w4",
      "/user/w5"
    )

    val routerGroup = system.actorOf(Props(classOf[RouterGroup], workers))

    routerGroup ! Work()
    routerGroup ! Work()

    Thread.sleep(100)
    system.terminate()
  }*/
}