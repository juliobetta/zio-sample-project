package com.maalka.logger

import zio.*
import zio.logging.*
import zio.logging.backend.SLF4J

// Note: see examples here https://github.com/zio/zio-logging/tree/master/examples/src/main/scala/zio/logging

object MaalkaLogger {
  val logFormat = "%s"

  val live: RuntimeConfigAspect = SLF4J.slf4j(LogLevel.All, LogFormat.annotation(logFormat))
}
