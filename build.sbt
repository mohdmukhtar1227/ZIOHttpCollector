ThisBuild / version := "0.1.0-SNAPSHOT"

ThisBuild / scalaVersion := "2.13.10"

lazy val root = (project in file("."))
  .settings(
    name := "ZIOHttpCollector"
  )


lazy val zioVersion="2.0.0"
lazy val ZIOHttpVersion = "1.0.0.0-RC27"
lazy val zioSchemaVersion  = "0.2.0-RC1-1"


libraryDependencies ++= Seq(
  "dev.zio" %% "zio" % zioVersion,
  "dev.zio" %% "zio-test" % zioVersion,
  "dev.zio" %% "zio-test-sbt" % zioVersion,
  "dev.zio" %% "zio-streams" % zioVersion,
  "dev.zio" %% "zio-test-junit" % zioVersion,
  "dev.zio" %% "zio-schema"            % zioSchemaVersion,
  "dev.zio" %% "zio-schema-json"       % zioSchemaVersion,
  "dev.zio" %% "zio-schema-derivation" % zioSchemaVersion
)

libraryDependencies ++= Seq(
  "dev.zio" %% "zio-kafka"   % "0.15.0",
  "dev.zio" %% "zio-json"    % "0.3.0-RC11",
  "io.d11" %% "zhttp" % "2.0.0-RC11",
)


libraryDependencies ++= Seq(
  "io.getquill"   %% "quill-zio"      % "4.3.0",
  "io.getquill"   %% "quill-jdbc-zio" % "4.3.0",
  "com.h2database" % "h2"             % "2.1.214"

)



testFrameworks += new TestFramework("zio.test.sbt.ZTestFramework")