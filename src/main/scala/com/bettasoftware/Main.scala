package com.bettasoftware

import com.bettasoftware.config.AppConfig
import com.bettasoftware.domain.HelloResponse
import com.bettasoftware.logger.AppLogger
import java.nio.file.Paths
import zhttp.http.*
import zio.*
import zio.logging.*
import zio.json.*
import zio.ExitCode
import zhttp.service.Server

object Main extends ZIOAppDefault {
  override def hook: RuntimeConfigAspect = AppLogger.live

  val app: Http[Any, Nothing, Request, Response] = Http.collectZIO[Request] {
    case Method.GET -> !! => ZIO.succeed(Response.ok)
    case Method.GET -> !! / "liveness" => ZIO.succeed(Response.text("OK"))
    case Method.GET -> !! / "wellness" => ZIO.succeed(Response.text("OK"))
    case Method.GET -> !! / "hello" =>
      ZIO.logDebug("Halo Halo!").as(Response.json(HelloResponse("Hey Heeeeey!").toJson))
  }

  val program: ZIO[System with AppConfig, Throwable, Unit] = for {
    config <- AppConfig.service
    _ <- ZIO.logDebug(s"CONFIG -> $config")
    port <- System.envOrElse("PORT", config.api.port.toString).map(_.toInt).orElseSucceed(config.api.port)
    _ <- ZIO.logDebug(s"Server started on port $port")
    _ <- Server.start(port, app)
  } yield ()

  override def run: URIO[ZEnv, ExitCode] =
    program
      .provideCustomLayer(AppConfig.live)
      .exitCode
}
