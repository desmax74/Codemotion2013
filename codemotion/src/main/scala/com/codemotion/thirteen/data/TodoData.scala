package com.codemotion.thirteen.data

import com.codemotion.thirteen.domain._
import java.util.concurrent.atomic.AtomicInteger

object TodoData {

  /** A counter variable to fake out auto-incrementing keys for us **/
  val idCounter = new AtomicInteger(3)

  /**
   * Some fake data so we can simulate retrievals.
   */
  var all = List(
      Todo(1, "Shampoo the cat"),
      Todo(2, "Wax the floor"),
      Todo(3, "Scrub the rug"))

  /** Returns the number of Todos which are not yet complete. **/
  def remaining = {
    all.filterNot(_.done == true).length
  }

  /** Adds a new Todo object to the existing list of todos, then sorts the list.
  */
  def add(todo: Todo): List[Todo] = {
    all ::= todo
    all = all.sortWith((e1, e2) => (e1.id < e2.id))
    all
  }

  /** Instantiates a new `Todo` object with an auto-incremented primary key id. **/
  def newTodo(name: String) = Todo(idCounter.incrementAndGet, name)

}
