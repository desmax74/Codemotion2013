package com.codemotion.thirteen.servlet

import org.scalatra.scalate.ScalateSupport
import org.scalatra.ScalatraServlet


class ScalateServlet extends ScalatraServlet with ScalateSupport{

  get("/") {
    contentType="text/html"

    ssp("/index", "foo" -> "uno", "bar" -> "dos")
  }

}
