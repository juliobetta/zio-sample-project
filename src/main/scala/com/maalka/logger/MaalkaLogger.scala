package com.maalka.logger

import zio.*
import zio.logging.LogAnnotation
import zio.logging.*
import zio.logging.slf4j.Slf4jLogger

// Note: see examples here https://github.com/zio/zio-logging/tree/master/examples/src/main/scala/zio/logging

object MaalkaLogger {
  val logFormat = "%s"

  val live: ULayer[Logging] = Slf4jLogger.make((context, message) => logFormat.format(message))
}
