package services

import play.api.cache._
import javax.inject._
import domain._

trait Persons {
  
  def findById(id: Long): Option[Person]
  
  def findAllByName(name: Option[String]): Seq[Person]
  
  def save(person: Person): Person
  
}