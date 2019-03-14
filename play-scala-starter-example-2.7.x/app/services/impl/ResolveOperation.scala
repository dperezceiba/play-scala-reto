package services.impl

import javax.inject._
import domain._
import services._

@Singleton()
class ResolveOperation extends Resolve {
  override def apply(operation: String, value1: Int, value2: Int): OutData = {
    operation match {
      case "add"      => OutData(value1, value2, operation, value1 + value2)
      case "subtract" => OutData(value1,value2, operation,  value1 - value2)
      case "multiply" => OutData(value1, value2, operation,value1 * value2)
      case "divide"   => OutData(value1,value2,operation, value1 / value2)
      case _        => OutData(value1, value2, "NOT FOUND OPERATION", 0)
    }
  }
}