package domain

import play.api.libs.json._

case class Message (val user: String, val content: String){
}

object Message{
   implicit val inWrites = new Writes[Message] {
      def writes(data: Message) = Json.obj(
        "user" -> data.user,
        "content" -> data.content
      )
    }
   
    implicit val inReads = Json.reads[Message]
    
    implicit val inFormat = Json.using[Json.WithDefaultValues].format[Message]
  
}