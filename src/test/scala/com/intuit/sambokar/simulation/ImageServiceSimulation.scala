package com.intuit.sambokar.simulation

import com.intuit.sambokar.util._
import io.gatling.core.Predef._
import io.gatling.http.Predef._
import scala.concurrent.duration._
import com.intuit.sambokar.scenarios.{GetImage, PostUser}

class ImageServiceSimulation extends Simulation {

  val httpConf = http.baseURL(Environment.baseURL)
                      //.headers(Headers.commonHeader)

  val imageServiceScenarios = List(
    //PostAndGetUser.postAndGetUser.inject(atOnceUsers(1)),

    GetImage.getImage.inject(
      atOnceUsers(1),
      //rampUsers(100) over(1 seconds),
      //constantUsersPerSec(1000) during(15 seconds)
      rampUsersPerSec(1) to 10 during(30 seconds) // 6
      //rampUsersPerSec(10) to 20 during(10 minutes) randomized, // 7
      //splitUsers(1000) into(rampUsers(10) over(10 seconds)) separatedBy(10 seconds), // 8
      //splitUsers(1000) into(rampUsers(10) over(10 seconds)) separatedBy atOnceUsers(30), // 9
      //heavisideUsers(1000) over(20 seconds) // 10
    ),

    PostUser.postUser.inject(atOnceUsers(Environment.users.toInt))
      .throttle(reachRps(1) in (20 seconds), holdFor(30 seconds))
  )

  setUp(imageServiceScenarios)
    .protocols(httpConf)
    .maxDuration(1 minutes)
    .assertions(
      global.responseTime.max.lessThan(Environment.maxResponseTime.toInt)
    )

    /*
    * assertThat(
    global.failedRequests.count.is(0)
    * */
}