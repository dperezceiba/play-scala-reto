package services

object Operation extends Enumeration {
  type Operation = Value

  val SAVE = Value("SAVE")
  val UPDATE = Value("UPDATE")
  val DELETE = Value("DELETE")

}

import Operation._

trait MessageBrokerService {
  
  def sendQueue(operation: Operation, domain: String): Unit
  
  def receiveQueue(operation: Operation, domain: String) : Unit
  
}

