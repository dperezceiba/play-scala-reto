package domain

import play.api.libs.json._
import java.sql.Date

case class PersonCrud (val id: Long = 0L, val document: String, val name: String, val birthdate: Date)

object PersonCrud{
   implicit val personWrites = new Writes[PersonCrud] {
      def writes(data: PersonCrud) = Json.obj(
        "id" -> data.id,
        "document" -> data.document,
        "name" -> data.name,
        "birthdate" -> data.birthdate
      )
    }
   
    implicit val personReads = Json.reads[PersonCrud]
    
    implicit val personFormat = Json.using[Json.WithDefaultValues].format[PersonCrud]
  
}