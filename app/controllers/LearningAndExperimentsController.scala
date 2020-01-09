package controllers

import config.AppConfig
import javax.inject._
import play.api.mvc._

import scala.concurrent.ExecutionContext

@Singleton
class LearningAndExperimentsController @Inject()(
    cc: ControllerComponents,
    appConfig: AppConfig
)(implicit ec: ExecutionContext)
    extends AbstractController(cc) {

  def index(): Action[AnyContent] = Action {
    implicit request: Request[AnyContent] =>
      Ok(views.html.index())
  }

}
