import javax.inject.{Inject, Singleton}
import play.api.inject.ApplicationLifecycle
import services.MessageBrokerService
import services.Operation

@Singleton
class SystemGlobal @Inject()(appLifecycle: ApplicationLifecycle, messageBroker: MessageBrokerService){
  def initialize(): Unit = {

    println("Hello!")
    messageBroker.receiveQueue(Operation.SAVE, "person")

  }

  initialize()
}