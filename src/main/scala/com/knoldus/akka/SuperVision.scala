package com.knoldus.akka

import akka.actor.SupervisorStrategy.{Escalate, Restart, Resume, Stop}
import akka.actor.{Actor, ActorRef, ActorSystem, OneForOneStrategy, Props}
import com.knoldus.akka.Aphrodite.{RestartException, ResumeException, StopException}
import scala.concurrent.duration._
import scala.language.postfixOps

class Aphrodite extends Actor {
  override def preStart: Unit = {
    println(" Aphrodite  preStart hook....")
  }

  override def preRestart(reason: Throwable, message: Option[Any]): Unit = {
   println(s"Aphrodite preReStart hook...")
    super.preRestart(reason, message)
  }

  override def postRestart(reason: Throwable): Unit = {
   println(s"Aphrodite postRestart hook....")
    super.postRestart(reason)
  }

  override def postStop(): Unit = {
   println(s" Aphrodite postStop hook....")
    //super.postStop()
  }

  override def receive: Receive = {
    case "Resume" =>
      throw ResumeException
    case "Stop" =>
      throw StopException
    case "Restart" =>
      throw RestartException
    case _ =>
      throw  new Exception
  }
}

object Aphrodite {
  case object ResumeException extends Exception
  case object StopException extends Exception
  case object RestartException extends Exception

  def props: Props = Props[Aphrodite]
}

class Hera extends Actor {
  import Aphrodite._

  var childRef: ActorRef = _
   override val supervisorStrategy: OneForOneStrategy = OneForOneStrategy(maxNrOfRetries = 10, withinTimeRange = 1 second) {
    case ResumeException =>
      Resume
    case RestartException =>
      Restart
    case StopException =>
      Stop
    case _ => Escalate
  }

  override def preStart(): Unit = {
    childRef = context.actorOf(Aphrodite.props, "Aphrodite")
    Thread.sleep(100)/*def main(args: Array[String]): Unit = {
    val system = ActorSystem("Supervision")
    val actor = system.actorOf(Props[Hera], "hera")
    //actor ! "Resume"
    actor ! "Stop"
    //actor ! "Restart"
    Thread.sleep(100)
    println()
    system.terminate()
  }*/
  }

  override def receive: Receive = {
    case msg =>
      println(s" Hera received $msg")
      childRef ! msg
      Thread.sleep(100)
  }
}

object SuperVision {



}
