package com.intuit.sambokar.scenarios
import com.intuit.sambokar.util._
import io.gatling.core.Predef._
import io.gatling.http.Predef._
object GetImage {
  val jsonUrlFeeder = jsonUrl(Environment.baseURL.concat("/getAllFileNames")).random
  val getImageHttp = http("get image")
    .get("/getFile?fileName=${id}")
    //.get("/getFile?fileName=1520944101975-homer.jpg")
    .header(HttpHeaderNames.Accept, HttpHeaderValues.ImageJpeg)
    .check(status.is(200))

  val getImage = scenario("get image")
      .forever() {
        exec(http("get all image names")
          .get("/getAllFileNames"))
          .feed(jsonUrlFeeder)
          .exec(
            getImageHttp
          )
      }


  val getLargeImage = scenario("get large image")
    .forever() {
      exec(http("get large image")
        .get("/getFile?fileName=mickeyBig.jpg")
        .header(HttpHeaderNames.Accept, HttpHeaderValues.ImageJpeg)
        .check(status.is(200))
        )
    }
}
