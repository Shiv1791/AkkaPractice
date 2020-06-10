package com.knoldus.akka

import akka.actor.{Actor, ActorSystem, FSM, Props, Stash}
import com.knoldus.akka.UserStorage.{Connect, DBOperation, Disconnect, Operation}

object UserStorageFSM {
  //FSM State
  sealed trait State
  case object Connected extends State
  case object Disconnected extends State

  //FSM Data
  sealed trait Data
  case object EmptyData extends Data
}

class UserStorageFSM extends FSM[UserStorageFSM.State, UserStorageFSM.Data] with Stash {
  import UserStorageFSM._

  startWith(Disconnected, EmptyData)

  when(Disconnected){
    case Event(Connect,_) =>
      println(s"User storage connected to DB")
      unstashAll()
      goto(Connected) using(EmptyData)
    case Event(_,_) =>
      stash()
      stay() using(EmptyData)
  }

  when(Connected){
    case Event(Disconnect, _) =>
      println(s"User storage disconnected from DB")
      goto(Disconnected) using(EmptyData)
    case Event(Operation(op, user), _) =>
      println(s"User storage received ${op} to do in user: ${user}")
      stay() using(EmptyData)
  }

  initialize()
}

/*object BecomeFSM extends App {
  import UserStorage._

  val system = ActorSystem("becomefsm")

  //val userStorage = system.actorOf(Props[UserStorage], "userStorage")
  val userStorageFSM = system.actorOf(Props[UserStorageFSM], "userStorageFSM")

  userStorageFSM ! Connect

  userStorageFSM ! Operation(DBOperation.Create, Some(User("shiv", "shiv@knoldus.com")))

  userStorageFSM ! Disconnect

  /*
    userStorage ! Connect

    userStorage ! Operation(DBOperation.Create, Some(User("shiv", "shiv@knoldus.com")))

    userStorage! Disconnect
  */

  Thread.sleep(100)

  system.terminate()
}*/
