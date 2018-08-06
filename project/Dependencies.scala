import sbt._

object Dependencies {
  lazy val confluentV = "4.1.0"
  lazy val kafkaV = "1.1.0"

  lazy val scalaTest = "org.scalatest" %% "scalatest" % "3.0.5"
  lazy val kafkaClients = "org.apache.kafka" % "kafka-clients" % kafkaV
  lazy val kafkaSchemaRegistryClient = "io.confluent" % "kafka-schema-registry-client" % confluentV
  lazy val kafkaAvroSerializer = "io.confluent" % "kafka-avro-serializer" % confluentV
  lazy val typeSafeConfig = "com.typesafe" % "config" % "1.3.2"
}
