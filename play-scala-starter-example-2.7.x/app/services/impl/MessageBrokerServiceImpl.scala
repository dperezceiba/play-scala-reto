package services.impl
import javax.inject._

import services.MessageBrokerService
import services.Operation._
import mq.MQProducer
import mq.MQConsumer

@Singleton()
class MessageBrokerServiceImpl extends MessageBrokerService {
  
  override def sendQueue(operation: Operation, domain: String): Unit = {
    val topic: String = operation.toString() + "-" + domain
    val channel = MQProducer.initiateRabbitMQProducer
    MQProducer.produce(channel, topic, s"Operation = $operation , Domain = $domain")
  }
  
  override def receiveQueue(operation: Operation, domain: String): Unit = {
    val topic: String = operation.toString() + "-" + domain
    MQConsumer.consume(topic)
  }
  
}