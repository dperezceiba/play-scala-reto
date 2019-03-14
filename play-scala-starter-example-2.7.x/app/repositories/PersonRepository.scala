package repositories

import javax.inject._
import scala.concurrent.{ Future, ExecutionContext }
import java.sql.Date
import domain.PersonCrud

trait PersonRepository {

  def save(document: String, name: String, birthdate: Date): Future[PersonCrud]

  def findById(id: Long): Future[Option[PersonCrud]]

  def delete(id: Long): Future[Int]
  
  def update(id: Long, person: PersonCrud): Future[Int]
  
  def findAll(): Future[Seq[PersonCrud]]
  
}

