package domain

import play.api.libs.json._

case class ErrorIn (val value1: Int, val value2: Int, val value3: Int){
}

object ErrorIn{
   implicit val inWrites = new Writes[ErrorIn] {
      def writes(data: ErrorIn) = Json.obj(
        "value1" -> data.value1,
        "value2" -> data.value2,
        "value3" -> data.value3
      )
    }
   
    implicit val inReads = Json.reads[ErrorIn]
  
}