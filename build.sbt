ThisBuild / organization := "it.solaroli"
ThisBuild / scalaVersion := "3.6.4"     //  Latest
ThisBuild / usePipelining := true

// ThisBuild / scalacOptions ++=
//   Seq(
//     "-deprecation",
//     "-feature",
//     "-language:implicitConversions",
//     "-unchecked",
//     "-Xfatal-warnings",
//     "-Xcheck-macros",
//     "-Yexplicit-nulls", // experimental (I've seen it cause issues with circe)
//     "-Xkind-projector",
//     "-Wsafe-init", // experimental (I've seen it cause issues with circe)
//   ) ++ Seq("-rewrite", "-indent") ++ Seq("-source", "future")

lazy val root =
  project
    .in(file("."))
    .settings(name := "parser_test")
    .settings(version := "0.0.1-SNAPSHOT")
    .settings(dependencies)
    .settings(testFrameworks += new TestFramework("zio.test.sbt.ZTestFramework"))

lazy val dependencies = Seq(
  libraryDependencies ++= Seq(
    "dev.zio" %%  "zio"         % "2.1.16",
    "dev.zio" %%  "zio-parser"  % "0.1.11",
  ),

  libraryDependencies ++= Seq(
    "dev.zio"       %% "zio-test"       % "2.1.16",
    "dev.zio"       %% "zio-test-sbt"   % "2.1.16",
    "org.scalatest" %% "scalatest"      % "3.2.19",     //  https://www.baeldung.com/scala/scalatest
  ).map(_ % Test),
)