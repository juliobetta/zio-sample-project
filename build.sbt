import com.typesafe.sbt.packager.docker.DockerPlugin.autoImport.dockerBaseImage

ThisBuild / scalaVersion     := "3.1.0"
ThisBuild / version          := "0.1.0-SNAPSHOT"
ThisBuild / organization     := "com.bettasoftware"
ThisBuild / organizationName := "Betta Software"

val zioVersion = "2.0.0-RC2"
val zioHttpVersion = "2.0.0-RC4"
val zioConfigVersion = "3.0.0-RC2"
val zioLoggingVersion = "2.0.0-RC5"
val zioJsonVersion = "0.3.0-RC3"
val slf4jVersion = "1.7.36"
val logbackClassicVersion = "1.2.10"
val logstashLogbackEncoder = "7.0.1"
val calibanVersion = "2.0.0-RC2"

lazy val root = (project in file("."))
  .settings(
    name := "zio-sample-project",

    dockerRepository := Some("juliobetta"),
    dockerBaseImage := "anapsix/alpine-java",
    dockerUpdateLatest := false,
    dockerExposedPorts := Seq(9000),

    // this disables appending the scala version to the produced binary when deployed to binary repo
    crossPaths := false,
    // This forces the compiler to create a jar for this project and include that in the classpath
    // If not set the compiler will use the classes directly
    exportJars := true,

    libraryDependencies ++= Seq(
      "dev.zio" %% "zio" % zioVersion,
      "dev.zio" %% "zio-test" % zioVersion % Test,

      "dev.zio" %% "zio-json" % zioJsonVersion,

      // Configuration
      "dev.zio" %% "zio-config" % zioConfigVersion,
      "dev.zio" %% "zio-config-magnolia" % zioConfigVersion,
      "dev.zio" %% "zio-config-typesafe" % zioConfigVersion,

      // HTTP
      "io.d11" %% "zhttp" % zioHttpVersion,
      "io.d11" %% "zhttp" % zioHttpVersion % Test,

      // Logging
      "dev.zio" %% "zio-logging" % zioLoggingVersion,
      "dev.zio" %% "zio-logging-slf4j" % zioLoggingVersion,
//      "org.slf4j" % "slf4j-api" % slf4jVersion,
//      "org.slf4j" % "slf4j-simple" % slf4jVersion,
      "ch.qos.logback" % "logback-classic" % logbackClassicVersion,
      "net.logstash.logback" % "logstash-logback-encoder" % logstashLogbackEncoder,

      // Caliban (GraphQL)
      "com.github.ghostdogpr" %% "caliban" % calibanVersion,
      "com.github.ghostdogpr" %% "caliban-zio-http" % calibanVersion
    ),
    testFrameworks += new TestFramework("zio.test.sbt.ZTestFramework")
  )
  .enablePlugins(
    RevolverPlugin,
    JavaAppPackaging,
  )

Global / onChangedBuildSource := ReloadOnSourceChanges
