package com.intuit.sambokar.util

import java.util

object Environment {
  val baseURL = scala.util.Properties.envOrElse("baseURL", "http://localhost:9080/storage")
  val users = scala.util.Properties.envOrElse("numberOfUsers", "10")
  val maxResponseTime = scala.util.Properties.envOrElse("maxResponseTime", "5000") // in milliseconds

}
