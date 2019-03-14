package services.impl

import javax.inject._
import services._
import domain._
import java.util.concurrent.atomic.AtomicLong

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
    if(name.isEmpty){
      data.toSeq
    }else{
      // For Comprehension -> Se utiliza para realizar una iteracion 
      // y filtrar registros los cuales se van almacenado en otra lista
      
      val filter = for(person <- data if(person.name.startsWith(name.get))) yield person
      filter.toSeq
    }
    // name.fold(data.toSeq)(x => data.filter(p => p.name.startsWith(x)).toSeq)
  }
  
}