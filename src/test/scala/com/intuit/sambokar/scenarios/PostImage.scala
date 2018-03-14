package com.intuit.sambokar.scenarios

import io.gatling.core.Predef._
import io.gatling.http.Predef._

object PostImage {

  /*val postUserHttp = http("post image")
    .post("/uploadFile")
    .upload("myImg", "homer.jpg", "image/jpeg")
    //.saveAs("fileUrl"))
    .check(status is 201)*/

  val postImage = scenario("post image")
    .exec(http("Post image")
      .post("/uploadFile")
      .bodyPart(RawFileBodyPart("file", "homer.jpg")
        .fileName("homer.jpg")
        .transferEncoding("binary")).asMultipartForm
      .check(regex(".*").saveAs("fileUrl"))
      .check(status is 200))
    //.exec(http("Get Uploaded Image")
      //.get("${fileUrl}")
      //.check(status is 200))
    .exec(http("Delete Image")
      .delete("/deleteFile?url=${fileUrl}")
        .check(regex("Successfully deleted"))
        .check(status is 200))

}