package com.codemotion.thirteen.servlet

import org.scalatra._
import scalate.ScalateSupport
import com.codemotion.thirteen.data.TodoData
import com.codemotion.thirteen.CodemotionStack
import org.slf4j.{Logger, LoggerFactory}

class FrontServlet extends ScalatraServlet with ScalateSupport {

  val logger =  LoggerFactory.getLogger(getClass)
  //inline html
  get("/template/inline") {
    logger.info("call /template/inline")

    <html>
      <body>
        <h1>Hello, world!</h1>
        Say <a href="hello-scalate">hello to Scalate</a>.
      </body>
    </html>
  }

  get("/template/") {
    logger.info("call /template")
      contentType="text/html"
    layoutTemplate("/WEB-INF/templates/views/index.ssp")
    ssp("/index", "title" -> "Scalatra: a tiny, Sinatra-like web framework for Scala", "bar" -> "dos")
  }

  get("/template/:id") {
     TodoData.all find (_.id == params("id").toInt) match {
       case Some(todo) => todo
       case None => halt(404)
     }
   }


  get("/template/all") {
       request.body
      request.cookies
    request.isAjax
    request.serverPort
    request.getSession
    request.multiCookies
    request.locale
    response.getOutputStream
    val idString = params("id")
    val id = params.getAs[Int]("id")
     val one = multiParams("captures")
   }

  
}
