package models.forms

import play.api.data.Form
import play.api.data.Forms._

object KafkaingForm {

  val kafkaingForm = Form(single("kafkaWriteInput" -> text))

}
