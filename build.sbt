val ScalatraVersion = "2.8.2"

ThisBuild / scalaVersion := "2.13.4"
ThisBuild / organization := "com.theguardian"

lazy val hello = (project in file("."))
  .settings(
    name := "sports-service",
    version := "0.1.0-SNAPSHOT",
    libraryDependencies ++= Seq(
      "org.scalatra" %% "scalatra" % ScalatraVersion,
      "org.scalatra" %% "scalatra-scalatest" % ScalatraVersion % Test,
      "org.scalamock" %% "scalamock" % "5.2.0" % Test,
      "ch.qos.logback" % "logback-classic" % "1.2.3" % "runtime",
      "org.eclipse.jetty" % "jetty-webapp" % "11.0.9" % "container",
      "javax.servlet" % "javax.servlet-api" % "3.1.0" % "provided",
      "org.scalatra" %% "scalatra-json" % "2.8.2",
      "org.json4s" %% "json4s-jackson" % "4.0.5",
      "org.dispatchhttp" %% "dispatch-core" % "1.2.0",
      "com.amazonaws" % "aws-java-sdk-s3" % "1.12.226",
    ),
  )

enablePlugins(SbtTwirl)
enablePlugins(JettyPlugin)
