import Dependencies._

lazy val root = (project in file(".")).
  settings(
    inThisBuild(List(
      organization := "com.example",
      scalaVersion := "2.12.6",
      version      := "0.1.0-SNAPSHOT"
    )),
    name := "pocavro",
    resolvers += "confluent" at "http://packages.confluent.io/maven/",
    libraryDependencies ++= { Seq(
      scalaTest % Test,
      kafkaClients,
      kafkaSchemaRegistryClient,
      kafkaAvroSerializer,
      typeSafeConfig
    )}
  )
