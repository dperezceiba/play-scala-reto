package services

import play.api.cache._
import javax.inject._
import models._
import scala.concurrent.Future
import java.util.concurrent.atomic.AtomicLong

trait Persons {
  
  def findById(id: Long): Option[Person]
  
  def findAllByName(name: Option[String]): Seq[Person]
  
  def save(person: Person): Person
  
}

@Singleton()
class PersonsImpl extends Persons{
  
  private val atomicCounter = new AtomicLong()
  var data = scala.collection.mutable.Set[Person]();
  
  override def save(person: Person): Person = {
    println(data.size)
    val register = Person(atomicCounter.getAndIncrement() + 1, person.document, person.name)
    data += (register)
    register
  }
  
  override def findById(id: Long): Option[Person] = {
    data.find(p => p.id == id)
  }
  
  override def findAllByName(name: Option[String]): Seq[Person] = {
    name.fold(data.toSeq)(x => data.filter(p => p.name.startsWith(x)).toSeq)
  }
  
}