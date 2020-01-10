package controllers

import config.AppConfig
import javax.inject._
import play.api.mvc._
import services.{KafkaingProducerService, KafkaingService}
import models.forms.KafkaingForm._

import scala.concurrent.{ExecutionContext, Future}

@Singleton
class KafkaingController @Inject()(
    cc: ControllerComponents,
    appConfig: AppConfig,
    kafkaingService: KafkaingService,
    kafkaingProducerService: KafkaingProducerService
)(implicit ec: ExecutionContext)
    extends AbstractController(cc) {

  def show(success: Option[Boolean]): Action[AnyContent] = Action {
    implicit request =>
      Ok(views.html.kafkaing_input(kafkaingForm, success))
  }

  def submit(): Action[AnyContent] = Action.async { implicit request =>
    kafkaingForm.bindFromRequest.fold(
      formWithErrors => Future.successful(BadRequest("Bad request")), {
        kafkaWriteInput =>
          kafkaingProducerService
            .writeToKafka(kafkaWriteInput)
            .map(_ =>
              Redirect(controllers.routes.KafkaingController.show(Some(true))))
      }
    )
  }

  def showConsumedRecords(): Action[AnyContent] = Action.async {
    implicit request =>
      kafkaingService
        .readKafkaingMessagesFromDb()
        .map(
          kafkaingMessages =>
            Ok(views.html.kafkaing_consumed_records(kafkaingMessages))
        )
  }

}
