package services
import java.time.Duration
import java.util

import cakesolutions.kafka.KafkaConsumer
import cakesolutions.kafka.KafkaConsumer.Conf
import config.AppConfig
import javax.inject.Inject
import models.KafkaingMessage
import org.apache.kafka.clients.consumer.ConsumerRecords
import org.apache.kafka.common.serialization.StringDeserializer

import scala.collection.JavaConverters._
import scala.concurrent.{ExecutionContext, Future}

class KafkaingConsumerService @Inject()(
    kafkaingService: KafkaingService,
    appConfig: AppConfig
)(implicit ec: ExecutionContext) {

  val topic = "kafkaing_input"

  val consumer = KafkaConsumer(
    Conf(
      appConfig.kafkaConf,
      new StringDeserializer(),
      new StringDeserializer()
    )
  )

  consumer.subscribe(util.Arrays.asList(topic))

  Future {
    while (true) {
      val records: ConsumerRecords[String, String] =
        consumer.poll(Duration.ofSeconds(60))

      for (data <- records.asScala) {
        kafkaingService.writeKafkaingMessageToDb(
          KafkaingMessage(data.key(), data.value())
        )
      }
    }
  }

}
