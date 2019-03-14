package services

import javax.inject._
import domain._

trait Calculator {
  
   def calculate(data: InData): OutData

}

@Singleton()
class SimpleCalculator @Inject() (resolve: Resolve) extends Calculator {
  
  override def calculate(data: InData): OutData = resolve(data.operation, data.value1, data.value2)

}