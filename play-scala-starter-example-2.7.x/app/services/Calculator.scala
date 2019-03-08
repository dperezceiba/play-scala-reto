package services

import javax.inject._
import models._

trait Calculator {

  def add(data: InData): OutData

  def subtract(data: InData): OutData

  def multiply(data: InData): OutData

  def divide(data: InData): OutData

}

@Singleton()
class SimpleCalculator @Inject() (resolve: Resolve) extends Calculator {

  override def add(data: InData): OutData = resolve(Operation.ADD, data.value1, data.value2)

  override def subtract(data: InData): OutData = resolve(Operation.SUBTRACT, data.value1, data.value2)

  override def multiply(data: InData): OutData = resolve(Operation.MULTIPLY, data.value1, data.value2)

  override def divide(data: InData): OutData = resolve(Operation.DIVIDE, data.value1, data.value2)

}