package domain

import play.api.libs.json._

case class OutData (val value1: Int, val value2: Int, val operation: String = "",val result: Int = 0){
}

object OutData{
   implicit val dataWrites = new Writes[OutData] {
      def writes(data: OutData) = Json.obj(
        "value1" -> data.value1,
        "value2" -> data.value2,
        "result" -> data.result,
        "operation" -> data.operation
      )
    }
   
    implicit val dataReads = Json.reads[OutData]
    
    implicit val dataFormat = Json.using[Json.WithDefaultValues].format[OutData]
  
}