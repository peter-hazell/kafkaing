resolvers += Resolver.bintrayRepo("cakesolutions", "maven")

lazy val root = (project in file("."))
  .enablePlugins(PlayScala)
  .settings(
    name := "kafkaing",
    organization := "com.petehazell",
    version := "1.0-SNAPSHOT",
    scalaVersion := "2.11.12",
    PlayKeys.playDefaultPort := 9002,
    libraryDependencies ++= Seq(
      "org.scalatestplus.play" %% "scalatestplus-play" % "4.0.3" % Test,
      "org.mockito" % "mockito-all" % "1.10.19",
      "org.webjars" % "bootstrap" % "4.4.1",
      "org.scanamo" %% "scanamo-alpakka" % "1.0.0-M11",
      "net.cakesolutions" %% "scala-kafka-client" % "2.3.1",
      guice
    )
  )
