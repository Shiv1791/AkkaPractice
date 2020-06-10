package com.knoldus.testing

import scala.collection.immutable.ListMap
import scala

case class LRUCache[A, B](private val MAX_ENTRIES: Int)
{
  protected val cache = Ref(ListMap.empty[A, B])

  def getOrElse(key: A)(fn: => B): B = {
    get(key).getOrElse {
      val result = fn
      put(key, result)
      result
    }
  }

  def get(key: A): Option[B] = atomic {
    cache.get.get(key)
  }

  def put(key: A, value: B) = atomic {
    cache.alter { current =>
      val altered = current + (key -> value)
      if(altered.size > MAX_ENTRIES) altered.takeRight(MAX_ENTRIES) else altered
    }
  }

  def remove(key: A) = atomic {
    cache.alter { current => current - key }
  }
}


class LRUClass {

}
