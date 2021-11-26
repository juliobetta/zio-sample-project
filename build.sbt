ThisBuild / scalaVersion     := "3.1.0"
ThisBuild / version          := "0.1.0-SNAPSHOT"
ThisBuild / organization     := "com.maalka"
ThisBuild / organizationName := "Maalka"

val zioVersion = "1.0.12" // TODO: upgrade to 2.0.0-M6-2. zio-http does not support it yet... =(
val zioHttpVersion = "1.0.0.0-RC17"

lazy val root = (project in file("."))
  .settings(
    name := "maalka-zio",
    libraryDependencies ++= Seq(
      "dev.zio" %% "zio" % zioVersion,
      "dev.zio" %% "zio-test" % zioVersion % Test,

      "io.d11" %% "zhttp" % zioHttpVersion,
      "io.d11" %% "zhttp" % zioHttpVersion % Test
    ),
    testFrameworks += new TestFramework("zio.test.sbt.ZTestFramework")
  )
