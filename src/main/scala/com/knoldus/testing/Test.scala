package com.knoldus.testing

import akka.actor.{Actor, ActorSystem, Props}
import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future

class Count extends Actor {
  var count = 0
  override def receive: Receive = {
    case _: String =>
      count = count +1
      println("       "+count)
  }
}


object Main{

  var state = 0


  new Thread().start()
  def main(args: Array[String]): Unit = {
    val system = ActorSystem("Actor-Paafdths")
    val counter11 = system.actorOf(Props[Count],"Count")
    //println("print count "+ new Count().count)
    for(_<- 1 to 100) {
      Future {
        state +=1
      }

      counter11 ! "shiv"
    }
    println(state)
  }
}

/*
import scala.concurrent.Future
import scala.concurrent.ExecutionContext.Implicits.global

var count = 0

for (_ <- 1 until 100)
  Future{count = count + 1}

println(count)


*
*/
