package services
import cakesolutions.kafka.KafkaConsumer.{Conf => ConsumerConf}
import cakesolutions.kafka.KafkaProducer.{Conf => ProducerConf}
import cakesolutions.kafka.{KafkaConsumer, KafkaProducer}
import config.AppConfig
import javax.inject.Inject
import models.KafkaingMessage
import org.apache.kafka.common.serialization.{
  StringDeserializer,
  StringSerializer
}
import repositories.KafkaingRepository

import scala.concurrent.{ExecutionContext, Future}

class KafkaingService @Inject()(
    kafkaingRepository: KafkaingRepository,
    appConfig: AppConfig
)(implicit ec: ExecutionContext) {

  val producer = KafkaProducer(
    ProducerConf(
      appConfig.kafkaProducerConf,
      new StringSerializer(),
      new StringSerializer()
    )
  )

  val consumer = KafkaConsumer(
    ConsumerConf(
      appConfig.kafkaConsumerConf,
      new StringDeserializer(),
      new StringDeserializer()
    )
  )

  def writeKafkaingMessageToDb(
      kafkaingMessage: KafkaingMessage
  ): Future[Unit] = {
    kafkaingRepository.put(kafkaingMessage).map(_ => ())
  }

  def readKafkaingMessagesFromDb(): Future[List[KafkaingMessage]] =
    kafkaingRepository.getAll.map(_.collect {
      case Right(m) => m
    })

}
