package com.knoldus.akka

import akka.actor.{Actor, ActorSystem, Props, Stash}
import com.knoldus.akka.UserStorage.{Connect, DBOperation, Disconnect, Operation}

object UserStorage {
  trait  DBOperation

  object DBOperation {
    case object Create extends DBOperation
    case object Update extends DBOperation
    case object Delete extends DBOperation
    case object Read extends DBOperation
  }

  case object Connect
  case object Disconnect
  case class Operation(dbOperation: DBOperation, user: Option[User])
}

class UserStorage extends Actor with Stash {

  override def receive: Receive = disconnected
  def connected: Receive = {
    case Disconnect =>
      println(s"User storage disconnected from DB")
      context.unbecome()
    case Operation(op, user) =>
      println(s"User storage received ${op} to do in user: ${user}")
  }
  def disconnected: Receive = {
    case Connect =>
      println(s"User storage connected to DB")
      unstashAll()
      context.become(connected)
    case _ =>
      stash()
  }

}

/*object Become extends App {
  import UserStorage._

  val system = ActorSystem("become")

  val userStorage = system.actorOf(Props[UserStorage], "userStorage")

  userStorage ! Connect

  userStorage ! Operation(DBOperation.Create, Some(User("shiv", "shiv@knoldus.com")))

  userStorage! Disconnect

  Thread.sleep(100)

  system.terminate()
}*/
