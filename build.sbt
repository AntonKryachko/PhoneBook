name := """play-scala"""

version := "1.0-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayScala)

scalaVersion := "2.11.11"

  libraryDependencies ++= Seq(jdbc,
                              cache,
                              ws,
                              "org.scalatestplus.play" %% "scalatestplus-play" % "2.0.0"
                              )


