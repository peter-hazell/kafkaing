package config

import com.typesafe.config.{Config, ConfigFactory}
import javax.inject.Inject
import play.api.Configuration

class AppConfig @Inject()(config: Configuration) {

  val kafkaConf: Config = ConfigFactory.load()

}
