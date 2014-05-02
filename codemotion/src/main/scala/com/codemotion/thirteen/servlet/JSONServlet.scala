package com.codemotion.thirteen.servlet

import org.json4s.{DefaultFormats, Formats}
import org.scalatra.ScalatraServlet

import org.scalatra.json._

class JSONServlet extends ScalatraServlet with JacksonJsonSupport{

  protected implicit val jsonFormats: Formats = DefaultFormats

  case class JellyBean(id: Int, name: String, flavor:String)

  before() {
      contentType = formats("json")
  }

  get("/jellybs/all"){
    JellyBeanRepo.all
  }

  post("/jellybs/create") {
    val jb = parsedBody.extract[JellyBean]
  }


  object JellyBeanRepo {

    var all = List(
      JellyBean(1,"fragola", "dolce"),
      JellyBean(2,"cioccolato al latte", "dolce"),
      JellyBean(2,"cioccolato fondente", "amaro")
    )
  }


}
