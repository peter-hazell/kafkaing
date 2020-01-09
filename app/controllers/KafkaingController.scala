package controllers

import config.AppConfig
import javax.inject._
import play.api.mvc._
import services.KafkaingService

import scala.concurrent.ExecutionContext

@Singleton
class KafkaingController @Inject()(
    cc: ControllerComponents,
    appConfig: AppConfig,
    kafkaingService: KafkaingService
)(implicit ec: ExecutionContext)
    extends AbstractController(cc) {

  def show(): Action[AnyContent] = ???

}
