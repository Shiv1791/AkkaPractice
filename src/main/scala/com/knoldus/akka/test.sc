/*
import akka.actor.{Actor, ActorSystem, Props}

import scala.concurrent.Future
import scala.concurrent.ExecutionContext.Implicits.global

class Count extends Actor {
  var count = 0
  override def receive: Receive = {
    case _: String =>
      count = count +1
      println("       "+count)
  }
}

var state = 0
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


/*

    println(s"Actor reference for Counter11: $counter11")

    val Counter1Selection1 = system.actorSelection("Counter1")
    println(s"Actor Selection for Counter11: $Counter1Selection1")
*/



*/
