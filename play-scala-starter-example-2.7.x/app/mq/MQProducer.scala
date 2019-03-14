package mq

import com.rabbitmq.client._

object MQProducer {

  private val EXCHANGE_CRUD = "crud_exchange"

  def initiateRabbitMQProducer: Channel = {
    val factory = new ConnectionFactory
    factory.setHost("localhost")
    val connection = factory.newConnection
    val channel = connection.createChannel()
    channel.exchangeDeclare(EXCHANGE_CRUD, "topic")
    channel
  }

  def produce(channel: Channel, topic: String, msg: String): Unit = {
    channel.queueDeclare(topic, false, false, false, null);
    channel.basicPublish(EXCHANGE_CRUD, topic, null, msg.getBytes)
    println(s"Publish $EXCHANGE_CRUD ==> $topic");
  }

}