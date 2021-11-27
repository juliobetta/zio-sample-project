package com.maalka

import com.maalka.config.AppConfig
import java.nio.file.Paths
import zio._
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
    _ <- console.putStrLn(s"CONFIG -> $config")
    port <- system.envOrElse("PORT", config.api.port.toString).map(_.toInt).orElseSucceed(config.api.port)
    _ <- console.putStrLn(s"Server started on port $port")
    _ <- Server.start(port, app)
  } yield ()

  override def run(args: List[String]): URIO[zio.ZEnv, ExitCode] =
    program
      .provideCustomLayer(AppConfig.live)
      .exitCode
}
