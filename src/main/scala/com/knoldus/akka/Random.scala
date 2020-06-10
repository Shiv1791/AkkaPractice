package com.knoldus.akka

import akka.actor.{ActorSystem, Props}
import akka.routing.FromConfig
import com.knoldus.akka.Worker.Work

/*object Random extends App {

  val system = ActorSystem("random")
/*  val routerPool = system.actorOf(FromConfig.props(Props[Worker]), "random-router-pool")

  routerPool ! Work()
  routerPool ! Work()
  routerPool ! Work()
  routerPool ! Work()*/

  system.actorOf(Props[Worker], "w1")
  system.actorOf(Props[Worker], "w2")
  system.actorOf(Props[Worker], "w3")
  val workers = List(
    "/user/w1",
    "/user/w2",
    "/user/w3"
  )

  val routerGroup = system.actorOf(new RouterGroup(workers).props, "random-router-group")
  Thread.sleep(100)

  routerGroup ! Work()
  routerGroup ! Work()
  routerGroup ! Work()
  routerGroup ! Work()
  system.terminate()

}*/
