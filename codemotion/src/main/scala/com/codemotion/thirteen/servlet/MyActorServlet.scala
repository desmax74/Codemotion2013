package com.codemotion.thirteen.servlet

import akka.actor._
import akka.util.Timeout
import org.scalatra.{FutureSupport, Accepted, ScalatraServlet}
import akka.dispatch.ExecutionContext
import org.slf4j.LoggerFactory

class MyActorServlet(system:ActorSystem, codemotionActor:ActorRef) extends ScalatraServlet with FutureSupport {

  import _root_.akka.pattern.ask
  implicit val timeout = Timeout(10)

  val logger =  LoggerFactory.getLogger(getClass)

  // You'll see the output from this in the browser.
  get("/async") {
    codemotionActor ? "Do stuff and give me an answer"
  }

  // You'll see the output from this in your terminal.
  get("/fire-forget") {
    codemotionActor ! "Hey, you know what?"
    Accepted()
  }

  protected implicit def executor: ExecutionContext = system.dispatcher
}

class CodemotionActor extends Actor {
  def receive = {
    case "Do stuff and give me an answer" => sender ! "The answer is 42"
    case "Hey, you know what?" => println("Yeah I know... oh boy do I know")
  }
}
