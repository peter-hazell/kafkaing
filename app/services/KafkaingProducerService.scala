package services

import java.util.UUID

import cakesolutions.kafka.KafkaProducer
import cakesolutions.kafka.KafkaProducer.Conf
import config.AppConfig
import javax.inject.Inject
import org.apache.kafka.clients.producer.ProducerRecord
import org.apache.kafka.common.serialization.StringSerializer

import scala.concurrent.{ExecutionContext, Future}

class KafkaingProducerService @Inject()(appConfig: AppConfig)(
    implicit ec: ExecutionContext
) {

  val topic = "kafkaing"

  val producer = KafkaProducer(
    Conf(appConfig.kafkaConf, new StringSerializer(), new StringSerializer())
  )

  def writeToKafka(value: String): Future[Unit] = {
    val record = new ProducerRecord[String, String](
      topic,
      UUID.randomUUID().toString,
      value
    )

    producer.send(record).map(_ => ())
  }

}
