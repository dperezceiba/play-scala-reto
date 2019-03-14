import org.scalatestplus.play._
import play.api.mvc._
import services.Calculator
import models._
import play.api.test.Helpers._
import play.api.test._
import play.api.libs.json._
import controllers.PersonController
import services.Persons
import play.api.mvc.Action
import play.api.mvc.AnyContent
import scala.None
import play.api.mvc.AnyContent
import scala.concurrent._
import domain.Person
import play.api.libs.json.JsValue
import play.api.libs.json.Json
import scala.collection.Seq
import scala.collection.immutable.List
import services.impl.PersonsImpl

class PersonUnitSpec extends PlaySpec with Results {

  "PersonController#FindById" should {

    "return a person if exist" in {
      val persons: Persons = new PersonsImpl() {
        override def findById(id: Long): Option[Person] = if (id == 1) Some(Person(1, "1051", "Dario")) else None;
      }
      val controller = new PersonController(persons, stubControllerComponents())
      val result: Future[Result] = controller.findById(1).apply(FakeRequest())
      val apiResult = contentAsJson(result)
      val spec = Json.fromJson[Person](apiResult)

      spec match {
        case JsSuccess(data: Person, path: JsPath) => {
          data.document mustBe "1051";
        }
        case e => fail
      }

    }
  }

  "PersonController#findAllByName" should {

    "return list of persons" in {
      val persons: Persons = new PersonsImpl() {
        override def findAllByName(name: Option[String]): Seq[Person] = Seq(Person(1, "1051", "Dario"));
      }
      val controller = new PersonController(persons, stubControllerComponents())
      val result: Future[Result] = controller.findAllByName(Some("Dario")).apply(FakeRequest())

      status(result) mustEqual OK

      val json: JsValue = Json.parse(contentAsString(result))

      val r = json.as[Seq[Person]]

      r.length mustBe 1

    }
  }

}