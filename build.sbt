name := """play-scala"""

version := "1.0-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayScala)

scalaVersion := "2.11.11"

  libraryDependencies ++= Seq(
                              jdbc,
                              javaJdbc,
                              cache,
                              ws,
                              "org.postgresql" % "postgresql" % "9.3-1102-jdbc4",
                              "org.liquibase" % "liquibase-core" % "3.5.3",
                              "com.github.aselab" % "squeryl" % "0.9.5"
//                              ,"com.typesafe.slick" % "slick_2.11" % "3.2.0"
                              )


