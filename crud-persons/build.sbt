name := """crud-persons"""
organization := "com.co.ceiba"

version := "1.0-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayScala)

scalaVersion := "2.12.8"

libraryDependencies += guice
libraryDependencies += "org.scalatestplus.play" %% "scalatestplus-play" % "4.0.1" % Test

// Adds additional packages into Twirl
//TwirlKeys.templateImports += "com.co.ceiba.controllers._"

// Adds additional packages into conf/routes
// play.sbt.routes.RoutesKeys.routesImport += "com.co.ceiba.binders._"

libraryDependencies += guice
libraryDependencies += "com.typesafe.play" %% "play-slick" % "4.0.0"
libraryDependencies += "com.typesafe.play" %% "play-slick-evolutions" % "4.0.0"

libraryDependencies += "com.h2database" % "h2" % "1.4.197"

// https://mvnrepository.com/artifact/com.rabbitmq/amqp-client
libraryDependencies += "com.rabbitmq" % "amqp-client" % "5.6.0"

libraryDependencies += "org.scalamock" %% "scalamock" % "4.1.0" % Test
