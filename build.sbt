name := "untitled5"

version := "1.0"

scalaVersion := "2.11.12"

libraryDependencies += "org.apache.spark" %% "spark-core" % "2.2.3"
libraryDependencies += "org.apache.spark" %% "spark-streaming" % "2.2.3"
libraryDependencies += "org.apache.spark" % "spark-streaming-kafka-0-8_2.11" % "2.1.0"
libraryDependencies += "org.apache.kafka" % "kafka-streams" % "2.1.1"