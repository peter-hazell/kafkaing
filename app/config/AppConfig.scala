package config

import com.typesafe.config.{Config, ConfigFactory}
import javax.inject.Inject
import play.api.Configuration

class AppConfig @Inject()(config: Configuration) {

  val kafkaProducerConf: Config = ConfigFactory.load("kafka.producer")
  val kafkaConsumerConf: Config = ConfigFactory.load("kafka.consumer")

}
