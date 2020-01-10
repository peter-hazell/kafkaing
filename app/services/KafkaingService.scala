package services

import javax.inject.Inject
import models.KafkaingMessage
import repositories.KafkaingRepository

import scala.concurrent.{ExecutionContext, Future}

class KafkaingService @Inject()(kafkaingRepository: KafkaingRepository)(
    implicit ec: ExecutionContext
) {

  def readKafkaingMessagesFromDb(): Future[List[KafkaingMessage]] =
    kafkaingRepository.getAll.map(_.collect {
      case Right(m) => m
    })

  def writeKafkaingMessageToDb(
      kafkaingMessage: KafkaingMessage
  ): Future[Unit] = {
    kafkaingRepository.put(kafkaingMessage).map(_ => ())
  }

}
