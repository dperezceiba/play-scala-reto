package domain

import play.api.libs.json._

case class Person(val id: Long = 0L, val document: String, val name: String) {
}

object Person{
   implicit val personWrites = new Writes[Person] {
      def writes(data: Person) = Json.obj(
        "id" -> data.id,
        "document" -> data.document,
        "name" -> data.name
      )
    }
   
    implicit val personReads = Json.reads[Person]
    
    implicit val personFormat = Json.using[Json.WithDefaultValues].format[Person]
  
}