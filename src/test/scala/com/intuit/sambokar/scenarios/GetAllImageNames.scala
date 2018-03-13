package com.intuit.sambokar.scenarios


import io.gatling.core.Predef._
import io.gatling.http.Predef._

object GetAllImageNames {
  val getAllImagesHttp = http("get all image names")
                        .get("/getAllFileNames")
                        .check(status is 200)

  val getAllImages = scenario("Get All Image Names")
                .exec(getAllImagesHttp)
}
