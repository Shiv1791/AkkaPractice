package com.knoldus.akka

import akka.actor.{Actor, ActorSystem, Props}
import com.knoldus.akka.MusicController.{Play, Stop}
import com.knoldus.akka.MusicPlayer.{StartMusic, StopMusic}


object MusicController {

  sealed trait ControllerMessage
  case object Play extends ControllerMessage
  case object Stop extends ControllerMessage

  def props = {
    Props[MusicController]
  }

}

class MusicController extends Actor {

  override def receive: Receive = {
    case Play =>
      println("Start Music....")
    case Stop =>
      println("Stop Music")
  }

}

object MusicPlayer {
  sealed trait PlayMessage
  case object StopMusic extends PlayMessage
  case object StartMusic extends PlayMessage

}

class MusicPlayer  extends Actor {
  override def receive: Receive = {
    case StopMusic =>
      println("I don't want to stop music....")
    case StartMusic =>
      val controller = context.actorOf(MusicController.props, "controller")
      controller ! Play
    case _ =>
      println("Unknown message")
  }

}


object ActorCreation {
/*
  def main(args: Array[String]): Unit = {
    val actorSystem = ActorSystem("MusicActor")
    val musicPlayer = actorSystem.actorOf(Props[MusicPlayer], "player")
    musicPlayer ! StartMusic
    actorSystem.terminate()
  }*/

}
