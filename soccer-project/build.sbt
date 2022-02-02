ThisBuild / version := "0.1.0-SNAPSHOT"

ThisBuild / scalaVersion := "2.13.8"

lazy val root = (project in file("."))
  .settings(
    name := "soccer-project"
  )
libraryDependencies += "org.apache.spark" %% "spark-core" % "3.2.0"
libraryDependencies += "org.apache.spark" %% "spark-sql-kafka-0-10_2.12" % "3.1.0"
libraryDependencies+=  "org.apache.spark" % "spark-sql_2.10" % "3.2.0"
