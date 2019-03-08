package controllers

import play.api.mvc._
import org.scalatestplus.play._
import org.scalatestplus.play.guice._
import play.api.test._
import play.api.test.Helpers._
import org.scalatest.mockito.MockitoSugar
import services.PersonService
import scala.concurrent.ExecutionContext.Implicits.global
import org.scalamock.scalatest.MockFactory
import org.scalatest.FlatSpec
import scala.concurrent.Future
import domain.Person
import scala.util.Success
import scala.util.Failure
import play.api.libs.json._
import org.scalatest.MustMatchers
import java.sql.Date
import org.joda.time.LocalDate

/**
 * Add your spec here.
 * You can mock out a whole application including requests, plugins etc.
 *
 * For more information, see https://www.playframework.com/documentation/latest/ScalaTestingWithScalaTest
 */
class PersonControllerSpec extends PlaySpec with GuiceOneAppPerSuite {

  "PersonController" should {

    "test Find ALL Persons" in {
      val result = contentAsString(route(app, FakeRequest(GET, "/persons")).get)
      result must include("[]")
    }

    "test Find By Id Not Found" in {
      val result = route(app, FakeRequest(GET, "/persons/1")).get
      status(result) mustBe NOT_FOUND
    }

    "test Save Person" in {
      val testData = Person(0L, "1051", "Dario", new Date(new LocalDate(1990, 6, 8).toDate().getTime))

      val result = route(app, FakeRequest(POST, "/persons").withBody(Json.toJson(testData))).get
      val appResult = contentAsJson(result).as[Person]
      testData.name mustEqual "Dario"
    }

    "test Find By Id With Results" in {
      val testData = Person(0L, "1051", "Dario", new Date(new LocalDate(1990, 6, 8).toDate().getTime))
      val result = route(app, FakeRequest(POST, "/persons").withBody(Json.toJson(testData))).get
      val appResult = contentAsJson(result).as[Person]
      
      val resultTest = route(app, FakeRequest(GET, "/persons/"+appResult.id)).get
      status(resultTest) mustBe OK
    }
    
    "test Save Person WITH Problemas" in {
      val testData = Person(0L, "1051", "Dario", new Date(new LocalDate(2010, 6, 8).toDate().getTime))
      val result = route(app, FakeRequest(POST, "/persons").withBody(Json.toJson(testData))).get
      status(result) mustBe INTERNAL_SERVER_ERROR
    }
    
    "test Update Person Id Not Found" in {
      val testData = Person(0L, "1051", "Dario", new Date(new LocalDate(1990, 6, 8).toDate().getTime))
      val result = route(app, FakeRequest(PUT, "/persons/100").withBody(Json.toJson(testData))).get
      status(result) mustBe NOT_FOUND
    }
    
    "test Update Person Id Whith Results" in {
      val testData = Person(0L, "1051", "Dario", new Date(new LocalDate(1990, 6, 8).toDate().getTime))
      val result = route(app, FakeRequest(POST, "/persons").withBody(Json.toJson(testData))).get
      val appResult = contentAsJson(result).as[Person]
      
      val testDataUpdate = Person(0L, "1051", "Camilo", new Date(new LocalDate(1990, 6, 8).toDate().getTime))
      val resultTest = route(app, FakeRequest(PUT, "/persons/"+appResult.id).withBody(Json.toJson(testDataUpdate))).get
      status(resultTest) mustBe OK
    }
    
    "test DELETE By Id Not Found" in {
      val result = route(app, FakeRequest(DELETE, "/persons/156")).get
      status(result) mustBe NOT_FOUND
    }
    
    "test Delete Person Id Whith Results" in {
      val testData = Person(0L, "1051", "Dario", new Date(new LocalDate(1990, 6, 8).toDate().getTime))
      val result = route(app, FakeRequest(POST, "/persons").withBody(Json.toJson(testData))).get
      val appResult = contentAsJson(result).as[Person]
      
      val resultTest = route(app, FakeRequest(DELETE, "/persons/"+appResult.id)).get
      status(resultTest) mustBe OK
    }
    
    "test Find ALL Persons with results" in {
      val testData = Person(0L, "1051", "Dario", new Date(new LocalDate(1990, 6, 8).toDate().getTime))
      route(app, FakeRequest(POST, "/persons").withBody(Json.toJson(testData))).get
      route(app, FakeRequest(POST, "/persons").withBody(Json.toJson(testData))).get
      route(app, FakeRequest(POST, "/persons").withBody(Json.toJson(testData))).get
      val result = contentAsJson(route(app, FakeRequest(GET, "/persons")).get)
      val apiResult = result.as[Seq[Person]]
      
      apiResult(0).name mustEqual testData.name
      assert(apiResult.size > 0)
    }

  }

}
