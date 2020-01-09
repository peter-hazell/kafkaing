package repositories
import akka.actor.ActorSystem
import akka.stream.ActorMaterializer
import akka.stream.alpakka.dynamodb.{DynamoClient, DynamoSettings}
import models.KafkaingMessage
import org.scanamo.error.DynamoReadError
import org.scanamo._
import org.scanamo.syntax._
import org.scanamo.auto._

import scala.concurrent.{ExecutionContextExecutor, Future}

class KafkaingRepository {

  implicit val system: ActorSystem = ActorSystem("scanamo-alpakka")
  implicit val materializer: ActorMaterializer =
    ActorMaterializer.create(system)
  implicit val executor: ExecutionContextExecutor = system.dispatcher

  val settings = DynamoSettings(system)
  val client = DynamoClient(settings)

  val table: Table[KafkaingMessage] = Table[KafkaingMessage]("kafkaing")

  def getAll: Future[List[Either[DynamoReadError, KafkaingMessage]]] = {
    val ops = table.scan()
    ScanamoAlpakka(client).execFuture(ops)
  }

  def put(
      kafkaingMessage: KafkaingMessage
  ): Future[Option[Either[DynamoReadError, KafkaingMessage]]] = {
    val ops = table.put(kafkaingMessage)
    ScanamoAlpakka(client).execFuture(ops)
  }
}
