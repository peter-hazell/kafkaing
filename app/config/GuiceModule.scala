package config
import com.google.inject.AbstractModule
import services.KafkaingConsumerService

class GuiceModule extends AbstractModule {

  override def configure(): Unit = {
    bind(classOf[KafkaingConsumerService]).asEagerSingleton()
  }

}
