package com.intuit.sambokar.scenarios

import io.gatling.core.Predef._
import io.gatling.http.Predef._

object GetImage {

  val getImageHttp = http("get image")
    .get("/getFile?fileName=1520944101975-homer.jpg")
    .header(HttpHeaderNames.Accept, HttpHeaderValues.ImageJpeg)
    .check(status.is(200))

  val getImage = scenario("get image")
     .exec(
       getImageHttp
     )
}
