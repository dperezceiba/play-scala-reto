package mq

import com.rabbitmq.client._
import java.io.IOException

object MQConsumer {
  private val EXCHANGE_CRUD = "crud_exchange"

  def consume(topic: String): Unit = {
    val connectionFactory = new ConnectionFactory
    connectionFactory.setHost("localhost")
    val connection = connectionFactory.newConnection
    val channel = connection.createChannel
    channel.exchangeDeclare(EXCHANGE_CRUD, "topic")

    channel.queueDeclare(topic, false, false, false, null);
    channel.queueBind(topic, EXCHANGE_CRUD, topic)

    val consumer = new DefaultConsumer(channel) {
      @throws(classOf[IOException])
      override def handleDelivery(consumerTag: String, envelope: Envelope, properties: AMQP.BasicProperties, body: Array[Byte]) {
        try {
          println(s"Consumer $EXCHANGE_CRUD ==> ["+new String(body))
        } catch {
          case e: IOException => println(e.getMessage)
        }
      }
    }

    channel.basicConsume(topic, true, consumer)
  }

}