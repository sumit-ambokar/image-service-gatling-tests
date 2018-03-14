package com.intuit.sambokar.util

import java.util

object Environment {
  val baseURL = scala.util.Properties.envOrElse("baseURL", "http://localhost:9080/storage")
  val users = scala.util.Properties.envOrElse("numberOfUsers", "10")
  val usersStressTest = scala.util.Properties.envOrElse("numberOfUsersStressTest", "100")
  val maxResponseTime = scala.util.Properties.envOrElse("maxResponseTime", "10000") // in milliseconds
  val maxTestDurationMins = scala.util.Properties.envOrElse("maxTestDurationMins", "1") // in milliseconds
  val reqPassRate95Min = scala.util.Properties.envOrElse("reqPassRateMin", "95")
  val maxRespTimeFor95Pass = scala.util.Properties.envOrElse("maxRespTimeFor95Pass", "2000")

}
