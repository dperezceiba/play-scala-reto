package repositories

import domain._
import javax.inject._
import scala.concurrent.{ Future, ExecutionContext }
import java.sql.Date

trait PersonRepository {

  def save(document: String, name: String, birthdate: Date): Future[Person]

  def findById(id: Long): Future[Option[Person]]

  def delete(id: Long): Future[Int]
  
  def update(id: Long, person: Person): Future[Int]
  
  def findAll(): Future[Seq[Person]]
  
}

