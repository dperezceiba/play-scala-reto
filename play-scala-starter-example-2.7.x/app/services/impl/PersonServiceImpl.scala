package services.impl

import domain._
import javax.inject._
import repositories.PersonRepository
import scala.concurrent.Future

import services.PersonService
import services.MessageService
import services.rules.PersonRule
import exceptions.RuleException
import services.MessageBrokerService
import services.Operation

@Singleton()
class PersonServiceImpl @Inject() (personRepository: PersonRepository, messageBrokerService: MessageBrokerService) extends PersonService {

  override def save(person: PersonCrud): Future[PersonCrud] = {
    if (!PersonRule.validPerson(person)) {
      Future.failed(new RuleException("Person no valid"))
    } else {
      val x = personRepository.save(person.document, person.name, person.birthdate)
      messageBrokerService.sendQueue(Operation.SAVE, "person")
      x
    }
  }

  override def findById(id: Long): Future[Option[PersonCrud]] = {
    personRepository.findById(id)
  }

  override def delete(id: Long): Future[Int] = {
    val x = personRepository.delete(id)
    messageBrokerService.sendQueue(Operation.DELETE, "person")
    x
  }

  override def update(id: Long, person: PersonCrud): Future[Int] = {
    if (!PersonRule.validPerson(person)) {
      Future.failed(new RuleException("Person no valid"))
    } else {
      val personAux = PersonCrud(id, person.document, person.name, person.birthdate)
      val x = personRepository.update(id, personAux)
      messageBrokerService.sendQueue(Operation.UPDATE, "person")
      x
    }
  }

  override def findAll(): Future[Seq[PersonCrud]] = {
    personRepository.findAll()
  }

}