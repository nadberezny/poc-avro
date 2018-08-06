package example

import java.util.Properties

import com.typesafe.config.Config
import org.apache.kafka.clients.consumer.KafkaConsumer
import org.apache.kafka.clients.producer._

import collection.JavaConverters._

object App extends App {
  val toProps = (conf: Config) => {
    val props = new Properties
    conf.entrySet().forEach(e => props.setProperty(e.getKey, conf.getString(e.getKey)))
    props
  }
  val schemaUrl = "http://localhost:8081"

  val props = new Properties()
  props.put("bootstrap.servers", "localhost:9092")
  props.put("acks", "all")
  props.put("key.serializer", "io.confluent.kafka.serializers.KafkaAvroSerializer")
  props.put("value.serializer", "io.confluent.kafka.serializers.KafkaAvroSerializer")

  props.put("group.id", "dupa")
  props.put("key.deserializer", "io.confluent.kafka.serializers.KafkaAvroDeserializer")
  props.put("value.deserializer", "io.confluent.kafka.serializers.KafkaAvroDeserializer")
  props.put("schema.registry.url", schemaUrl)

  val producer = new KafkaProducer[String, Int](props)

  val record = new ProducerRecord[String, Int]("my-topic", "woo", 1)

  producer.send(record)

  producer.close

  val consumer = new KafkaConsumer[String, Int](props)
  consumer.subscribe(List("my-topic").asJava)

  while (true) {
    val results = consumer.poll(20).asScala
    for (res <- results) {
      println(res)
      // Do stuff
    }
  }
}
//./bin/kafka-console-consumer.sh --bootstrap-server localhost:9092 --topic my-topic --from-beginning