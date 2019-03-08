package models
import javax.inject._

object Operation extends Enumeration {
  type Operation = Value

  val ADD = Value("ADD")
  val SUBTRACT = Value("SUBTRACT")
  val MULTIPLY = Value("MULTIPLY")
  val DIVIDE = Value("DIVIDE")

}

import Operation._

trait Resolve {
  def apply(operation: Operation, value1: Int, value2: Int): OutData
}

@Singleton()
class ResolveOperation extends Resolve {
  override def apply(operation: Operation, value1: Int, value2: Int): OutData = {
    operation match {
      case ADD      => OutData(value1, value2, ADD.toString(), value1 + value2)
      case SUBTRACT => OutData(value1,value2,SUBTRACT.toString(),  value1 - value2)
      case MULTIPLY => OutData(value1, value2, MULTIPLY.toString(),value1 * value2)
      case DIVIDE   => OutData(value1,value2,DIVIDE.toString(), value1 / value2)
      case _        => OutData(value1, value2, "NOT FOUND OPERATION", 0)
    }
  }
}