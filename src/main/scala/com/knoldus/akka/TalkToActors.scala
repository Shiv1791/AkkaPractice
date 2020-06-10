package com.knoldus.akka

import akka.actor.{Actor, ActorRef, ActorSystem, Props}
import akka.util.Timeout
import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.duration._
import akka.pattern.ask
import com.knoldus.akka.Checker.{BlockUser, CheckUser, WhiteUser}
import com.knoldus.akka.Recorder.NewUser
import com.knoldus.akka.Storage.AddUser
import scala.language.postfixOps

case class User(userName: String, email: String)

object Recorder {
  sealed trait RecorderMessage
  case class NewUser(user: User) extends RecorderMessage

  def props(checker: ActorRef, storage: ActorRef): Props = Props(new Recorder(checker, storage))

}

class Recorder(checker: ActorRef, storage: ActorRef) extends Actor {
  implicit val timeout: Timeout = Timeout(5 second)
  override def receive: Receive = {
    case NewUser(user) =>
      checker ? CheckUser(user) map {
        case WhiteUser(user) =>
          storage ! AddUser(user)
        case BlockUser(user) =>
          println(s" Recorder: $user is in the blacklist")
      }

  }
}


object Checker {
  sealed trait CheckerMessage
  sealed trait CheckerResponse
  case class CheckUser(user: User) extends CheckerMessage
  case class BlockUser(user: User) extends CheckerMessage
  case class WhiteUser(user: User) extends CheckerMessage
}

class Checker extends Actor {
  val blackListUser = List("Shiv", "raj", "Singh")
  override def receive: Receive = {
    case CheckUser(user)  if blackListUser.contains(user) =>
      println(s"Checker: $user is blacklist")
      sender() ! BlockUser(user)
    case CheckUser(user)  if !blackListUser.contains(user) =>
      println(s"Checker: $user is not in the blacklist")
      sender() ! WhiteUser(user)
  }
}

object Storage {
  sealed trait StorageMessage
  case class AddUser(user: User) extends StorageMessage

}

class Storage extends Actor {
  var users = List.empty[User]
  override def receive: Receive = {
    case AddUser(user) =>
      println(s"Storage: $user added")
      users = user::users
  }
}


object TalkToActors {

  /*def main(args: Array[String]): Unit = {
    val actorSystem = ActorSystem("talk-to-actor")
    val checker = actorSystem.actorOf(Props[Checker], "checker")
    val storage = actorSystem.actorOf(Props[Storage], "storage")
    val recorder = actorSystem.actorOf(Recorder.props(checker, storage), "recorder")
    recorder ! Recorder.NewUser(User("Prabhat", "prabhat@knoldus.com"))

    Thread.sleep(100)
    actorSystem.terminate()


    output:
    Checker: User(Prabhat,prabhat@knoldus.com) is not in the blacklist
    Storage: User(Prabhat,prabhat@knoldus.com) added

  }*/


}
