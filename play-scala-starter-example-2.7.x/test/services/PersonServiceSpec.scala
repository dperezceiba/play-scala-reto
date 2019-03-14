package services

import play.api.mvc._
import org.scalatestplus.play._
import org.scalatestplus.play.guice._
import play.api.test._
import play.api.test.Helpers._
import org.scalatest.mockito.MockitoSugar
import scala.concurrent.ExecutionContext.Implicits.global
import org.scalamock.scalatest.MockFactory
import org.scalatest.FlatSpec
import scala.concurrent.Future
import domain.PersonCrud
import scala.util.Success
import scala.util.Failure
import play.api.libs.json._
import org.scalatest.MustMatchers
import java.sql.Date
import org.joda.time.LocalDate
import services.impl.PersonServiceImpl
import repositories.PersonRepository
import org.scalatest.Matchers
import org.scalatest.FunSuite
import services.impl.MessageBrokerServiceImpl


/**
 * Add your spec here.
 * You can mock out a whole application including requests, plugins etc.
 *
 * For more information, see https://www.playframework.com/documentation/latest/ScalaTestingWithScalaTest
 */
class PersonServiceSpec extends FunSuite with Matchers with MockFactory  {
  
  test("Valid Find all of repository") {
    val messageBrokerService = mock[MessageBrokerService]
    val personRepository = mock[PersonRepository]
    
    val personService = new PersonServiceImpl(personRepository, messageBrokerService)
    
    (personRepository.findAll _).expects().returning(Future.apply(Seq[PersonCrud](PersonCrud(1L, "1051", "Dario", new Date(new LocalDate(1990, 6, 8).toDate().getTime)))))
    
    val result = personService.findAll()
    
    result.onComplete{
      case Success(data) => data.length shouldBe 1
      case Failure(t) => println("An error has occurred: " + t.getMessage)
    }
  }
  
  test("Not found person by Id") {
    val messageBrokerService = mock[MessageBrokerService]
    val personRepository = mock[PersonRepository]
    
    val personService = new PersonServiceImpl(personRepository, messageBrokerService)
    
    (personRepository.findById _).expects(1L).returning(Future.apply(None))
    
    val result = personService.findById(1L)
    
    result.onComplete{
      case Success(data) => data.isEmpty shouldBe true
      case Failure(t) => println("An error has occurred: " + t.getMessage)
    }
  }
  
  test("Save Person Form") {
    val messageBrokerService = new MessageBrokerServiceImpl()
    val personRepository = mock[PersonRepository]
    
    val personService = new PersonServiceImpl(personRepository, messageBrokerService)
    val person = PersonCrud(0L,"1051", "Dario", new Date(new LocalDate(1990, 6, 8).toDate().getTime))
    
    (personRepository.save _).expects(person.document, person.name, person.birthdate).returning(Future.apply(person))
    
    val result = personService.save(person)
    
    result.onComplete{
      case Success(data) => data.name shouldEqual "Dario"
      case Failure(t) => println("An error has occurred: " + t.getMessage)
    }
  }
  
  test("Delete Person By ID") {
    val messageBrokerService = new MessageBrokerServiceImpl()
    val personRepository = mock[PersonRepository]
    
    val personService = new PersonServiceImpl(personRepository, messageBrokerService)
    
    (personRepository.delete _).expects(1L).returning(Future.apply(1))
    
    val result = personService.delete(1L)
    
    result.onComplete{
      case Success(data) => data shouldBe 1
      case Failure(t) => println("An error has occurred: " + t.getMessage)
    }
  }
  
  test("Update Person Find By Id") {
    val messageBrokerService = new MessageBrokerServiceImpl()
    val personRepository = mock[PersonRepository]
    
    val personService = new PersonServiceImpl(personRepository, messageBrokerService)
    val person = PersonCrud(1L,"1051", "Dario", new Date(new LocalDate(1990, 6, 8).toDate().getTime))
    
    (personRepository.update _).expects(1L, person).returning(Future.apply(1))
    
    val result = personService.update(1L, person)
    
    result.onComplete{
      case Success(data) => data shouldBe 1
      case Failure(t) => println("An error has occurred: " + t.getMessage)
    }
  }
  
  
  
}
