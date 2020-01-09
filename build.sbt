lazy val root = (project in file("."))
  .enablePlugins(PlayScala)
  .settings(
    name := "learning-and-experiments",
    organization := "com.petehazell",
    version := "1.0-SNAPSHOT",
    scalaVersion := "2.11.12",
    PlayKeys.playDefaultPort := 9002,
    libraryDependencies ++= Seq(
      "org.scalatestplus.play" %% "scalatestplus-play" % "4.0.3" % Test,
      "org.mockito"            % "mockito-all"         % "1.10.19",
      "org.webjars"            % "bootstrap"           % "4.4.1",
      guice
    )
  )
