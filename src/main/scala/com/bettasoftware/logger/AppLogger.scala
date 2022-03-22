package com.bettasoftware.logger

import zio.*
import zio.logging._
import zio.logging.backend.SLF4J

// Note: see examples here https://github.com/zio/zio-logging/tree/master/examples/src/main/scala/zio/logging

object AppLogger {
  val live: RuntimeConfigAspect = SLF4J.slf4j(LogLevel.All, LogFormat.colored)
}
