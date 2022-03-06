package com.maalka

import com.maalka.config.AppConfig
import com.maalka.domain.HelloResponse
import com.maalka.logger.MaalkaLogger
import java.nio.file.Paths
import zhttp.http.*
import zio.*
import zio.logging.*
import zio.json.*
import zio.ExitCode
import zhttp.service.Server

object Main extends ZIOAppDefault {

  val app: Http[Any, Nothing, Request, Response] = Http.collect[Request] {
    case Method.GET -> !! / "hello" => Response.json("{ \"message\": \"Hello World\" }")
    case Method.GET -> !! / "test" => Response.json("{ \"message\": \"FOO!!!\" }")
  }

  val program: ZIO[System with AppConfig, Throwable, Unit] = for {
    config <- AppConfig.service
    _ <- ZIO.logDebug(s"CONFIG -> $config")
    port <- System.envOrElse("PORT", config.api.port.toString).map(_.toInt).orElseSucceed(config.api.port)
    _ <- ZIO.logInfo(s"Server started on port $port")
    _ <- Server.start(port, app)
  } yield ()

  override def run: URIO[ZEnv, ExitCode] =
    program
      .provideCustomLayer(AppConfig.live)
      .exitCode
}
