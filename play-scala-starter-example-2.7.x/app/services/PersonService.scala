package services

import domain._
import javax.inject._
import scala.concurrent.Future

trait PersonService {

  def save(person: PersonCrud): Future[PersonCrud]

  def findById(id: Long): Future[Option[PersonCrud]]

  def delete(id: Long): Future[Int]

  def update(id: Long, person: PersonCrud): Future[Int]

  def findAll(): Future[Seq[PersonCrud]]

}

