name := """projectmanager"""

version := "1.0-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayJava)

scalaVersion := "2.11.11"

libraryDependencies += javaJpa
libraryDependencies += javaJdbc
libraryDependencies += cache
libraryDependencies += javaWs
libraryDependencies += "org.hibernate" % "hibernate-core" % "5.2.5.Final"
libraryDependencies += "org.mariadb.jdbc" % "mariadb-java-client" % "1.5.7"

libraryDependencies += "org.webjars.bower" % "metismenu" % "2.0.3"

libraryDependencies += "com.google.apis" % "google-api-services-calendar" % "v3-rev264-1.23.0"

libraryDependencies += "org.webjars" % "font-awesome" % "4.7.0"

libraryDependencies += "org.webjars" % "chartjs" % "2.7.0"

libraryDependencies += "org.webjars" % "bootstrap-select" % "1.12.2"

libraryDependencies ++= Seq(
  "org.webjars" %% "webjars-play" % "2.5.0",
  "org.webjars" % "bootstrap" % "3.3.7-1" exclude("org.webjars", "jquery"),
  "org.webjars" % "jquery" % "3.2.1"
)