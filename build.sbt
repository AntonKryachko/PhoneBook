name := """play-scala"""

version := "1.0-SNAPSHOT"

scalaVersion := "2.11.11"

lazy val root = (project in file("."))
  .enablePlugins(PlayScala)


  libraryDependencies ++= Seq(
                              jdbc,
                              javaJdbc,
                              cache,
                              ws,
                              "org.postgresql" % "postgresql" % "9.3-1102-jdbc4",
                              "org.liquibase" % "liquibase-core" % "3.5.3",
                              "org.squeryl" %% "squeryl" % "0.9.5-7"
//                              ,"com.typesafe.slick" % "slick_2.11" % "3.2.0"
                              )


