package com.maalka.config

import java.io.File
import java.nio.file.Path
import scala.io.Source
import zio.*
import zio.config.magnolia.*
import zio.config._
import zio.config.typesafe.*


case class ApiConfig(port: Int)
case class DatabaseConfig(port: Int)
case class AppConfig(api: ApiConfig, db: DatabaseConfig)

object AppConfig {
  val appDescription = descriptor[AppConfig]

  val live: ZLayer[system.System, Nothing, Has[AppConfig]] =
    TypesafeConfig
      .fromHoconString(
        Source.fromResource("application.conf").getLines.mkString,
        appDescription
      )
      .orDie

  val service: URIO[Has[AppConfig], AppConfig] = ZIO.service[AppConfig]
}
