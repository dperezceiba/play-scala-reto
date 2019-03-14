import javax.inject.{ Inject, Singleton }
import play.api.inject.ApplicationLifecycle
import services.MessageBrokerService
import services.Operation
import akka.actor.ActorSystem
import akka.actor.Props
import tasks.MyTask
import akka.actor.ActorSystem
import scala.concurrent.duration._
import scala.concurrent.ExecutionContext

@Singleton
class SystemGlobal @Inject() (appLifecycle: ApplicationLifecycle, messageBroker: MessageBrokerService) {
  def initialize(): Unit = {

    println("Hello!")
    messageBroker.receiveQueue(Operation.SAVE, "person")
    messageBroker.receiveQueue(Operation.UPDATE, "person")
    messageBroker.receiveQueue(Operation.DELETE, "person")

  }

  initialize()
}