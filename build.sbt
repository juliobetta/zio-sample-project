ThisBuild / scalaVersion     := "3.1.0"
ThisBuild / version          := "0.1.0-SNAPSHOT"
ThisBuild / organization     := "com.maalka"
ThisBuild / organizationName := "Maalka"

val zioVersion = "1.0.12" // TODO: upgrade to 2.0.0-M6-2. zio-http does not support it yet... =(
val zioHttpVersion = "1.0.0.0-RC17"
val zioConfigVersion = "1.0.10"
val zioLoggingVersion = "0.5.14"
val slf4jVersion = "1.7.32"
val logbackClassicVersion = "1.2.7"
val logstashLogbackEncoder = "7.0"

lazy val root = (project in file("."))
  .settings(
    name := "maalka-zio",
    libraryDependencies ++= Seq(
      "dev.zio" %% "zio" % zioVersion,
      "dev.zio" %% "zio-test" % zioVersion % Test,

      // Configuration
      "dev.zio" %% "zio-config" % zioConfigVersion,
      "dev.zio" %% "zio-config-magnolia" % zioConfigVersion,
      "dev.zio" %% "zio-config-typesafe" % zioConfigVersion,

      // HTTP
      "io.d11" %% "zhttp" % zioHttpVersion,
      "io.d11" %% "zhttp" % zioHttpVersion % Test,

      // Logging
      "dev.zio" %% "zio-logging-slf4j" % zioLoggingVersion,
      "org.slf4j" % "slf4j-api" % slf4jVersion,
      "org.slf4j" % "slf4j-simple" % slf4jVersion,
      // TODO: replace slf4j-simple for more detailed logs
//      "ch.qos.logback" % "logback-classic" % logbackClassicVersion,
//      "net.logstash.logback" % "logstash-logback-encoder" % logstashLogbackEncoder,
    ),
    testFrameworks += new TestFramework("zio.test.sbt.ZTestFramework")
  )

enablePlugins(RevolverPlugin)

Global / onChangedBuildSource := ReloadOnSourceChanges
