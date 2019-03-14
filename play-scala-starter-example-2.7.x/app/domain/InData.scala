package domain

import play.api.libs.json._

case class InData (val value1: Int, val value2: Int, val operation: String){
}

object InData{
   implicit val inWrites = new Writes[InData] {
      def writes(data: InData) = Json.obj(
        "value1" -> data.value1,
        "value2" -> data.value2,
        "operation" -> data.operation
      )
    }
   
    implicit val inReads = Json.reads[InData]
    
    implicit val inFormat = Json.using[Json.WithDefaultValues].format[InData]
  
}