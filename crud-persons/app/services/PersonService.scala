package services

import domain._
import javax.inject._
import scala.concurrent.Future

trait PersonService {

  def save(person: Person): Future[Person]

  def findById(id: Long): Future[Option[Person]]

  def delete(id: Long): Future[Int]

  def update(id: Long, person: Person): Future[Int]

  def findAll(): Future[Seq[Person]]

}

