package com.intuit.sambokar.simulation

import com.intuit.sambokar.scenarios.{GetImage, PostImage}
import com.intuit.sambokar.util._
import io.gatling.core.Predef._
import io.gatling.http.Predef._

import scala.concurrent.duration._

class ImageServiceBottleneckSimulation extends Simulation {

  val httpConf = http.baseURL(Environment.baseURL)
                      //.headers(Headers.commonHeader)

  val imageServiceScenarios = List(

    GetImage.getLargeImage.inject(
      atOnceUsers(Environment.usersStressTest.toInt)
    )
  )

  setUp(imageServiceScenarios)
    .protocols(httpConf)
    .maxDuration(Environment.maxTestDurationMins.toInt minutes)
    .assertions(
      global.responseTime.max.lessThan(Environment.maxResponseTime.toInt),
      global.successfulRequests.percent.greaterThan(Environment.reqPassRate95Min.toInt),
      global.responseTime.percentile3.lessThan(Environment.maxRespTimeFor95Pass.toInt)
      //global.failedRequests.count.is(0)
    )
}