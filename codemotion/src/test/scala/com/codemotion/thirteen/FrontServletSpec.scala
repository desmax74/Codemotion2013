package com.codemotion.thirteen

import org.scalatra.test.specs2._
import servlet.FrontServlet

// For more on Specs2, see http://etorreborre.github.com/specs2/guide/org.specs2.guide.QuickStart.html
class FrontServletSpec extends ScalatraSpec {
  def is =
    "GET / on FrontServlet" ^
      "should return status 200" ! root200 ^
      end

  addServlet(classOf[FrontServlet], "/*")

  def root200 = get("/") {
    status must_== 200
  }
}
