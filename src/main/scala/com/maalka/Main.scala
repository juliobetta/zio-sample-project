package com.maalka

import com.maalka.config.AppConfig
import com.maalka.logger.MaalkaLogger
import java.nio.file.Paths
import zio._
import zio.logging._
import zio.{App, ExitCode}
import zhttp.http.*
import zhttp.service.Server

object Main extends App {

  val app = Http.collect[Request] {
    case Method.GET -> Root / "hello" => Response.jsonString("{ \"message\": \"Hello World!!!\" }")
    case Method.GET -> Root / "test" => Response.jsonString("{ \"message\": \"FOO!!!\" }")
  }

  // TODO: setup zio-logger
  val program = for {
    config <- AppConfig.service
    _ <- log.debug(s"CONFIG -> $config")
    port <- system.envOrElse("PORT", config.api.port.toString).map(_.toInt).orElseSucceed(config.api.port)
    _ <- log.info(s"Server started on port $port")
    _ <- Server.start(port, app)
  } yield ()

  override def run(args: List[String]): URIO[zio.ZEnv, ExitCode] =
    program
      .provideCustomLayer(AppConfig.live ++ MaalkaLogger.live)
      .exitCode
}
