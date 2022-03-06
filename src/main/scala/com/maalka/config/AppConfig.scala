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

// Docs: https://zio.github.io/zio-config/docs/quickstart/quickstart_index

object AppConfig {
  // TODO: figure out how to get some values from system env instead of config file
  val appDescription = descriptor[AppConfig]

  val live: ZLayer[System, Nothing, AppConfig] =
    TypesafeConfig
      .fromHoconString(
        Source.fromResource("application.conf").getLines.mkString,
        appDescription
      )
      .orDie

  val service: URIO[AppConfig, AppConfig] = ZIO.service[AppConfig]
}
