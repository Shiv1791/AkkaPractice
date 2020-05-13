package com.knoldus.classicakka

import akka.actor.{Actor, Props}
import akka.event.{Logging, LoggingAdapter}

class MyActor extends Actor {
  val log: LoggingAdapter = Logging(context.system, this)

  val props7: Props = Props(new MyActor)

  def receive: Receive = {
    case "test" => log.info("received test")
    case _      => log.info("received unknown message")
  }
}

object NewActor {

  case class MyValueClass(v: Int) extends AnyVal

  class ValueActor(value: MyValueClass) extends Actor {

    override def receive: Receive = {
      case multiplier: Long => sender() ! (value.v * multiplier)
    }
  }

  val valueClassProp: Props =  Props(classOf[ValueActor], MyValueClass(5))


  class DefaultValueActor(a: Int, b: Int) extends Actor {
    override def receive: Receive = {
      case x:Int => sender() ! ((a + x) * b)
    }
  }

  val defaultValueActorProp: Props = Props(classOf[DefaultValueActor], 2.0)


  class DefaultValueActor2(b: Int = 5) extends Actor {
    override def receive: Receive = {
      case x: Int => sender() ! x*b
    }
  }

  val defaultValueProp2: Props = Props[DefaultValueActor2] // Unsupported
  val defaultValueProp3: Props = Props(classOf[DefaultValueActor2]) // Unsupported
  //In both cases an IllegalArgumentException will be thrown stating no matching constructor could be found.

}