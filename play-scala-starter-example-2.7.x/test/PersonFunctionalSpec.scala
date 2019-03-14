import org.scalatestplus.play.guice.GuiceOneAppPerSuite
import org.scalatestplus.play._
import play.api.test.FakeRequest
import play.api.test.Helpers._
import play.api.libs.json._
import domain.Person
import scala.concurrent.Future
import play.api.mvc.Result

class PersonFunctionalSpec extends PlaySpec with GuiceOneAppPerSuite{
  
  "PersonController" should {

    "return operations persons" in {
      
      val testData1 = Person(0,"1051", "Dario")
      val testData2 = Person(0,"1071", "Juan")
      
      val result1: Future[Result] = route(app, FakeRequest(POST, "/persons").withJsonBody(Json.toJson(testData1))).get
      val apiResult1 = contentAsJson(result1)
      val person1: Person = apiResult1.as[Person]
      person1.id mustBe 1
      
      val result2: Future[Result] = route(app, FakeRequest(POST, "/persons").withJsonBody(Json.toJson(testData2))).get
      val apiResult2 = contentAsJson(result2)
      val person2: Person = apiResult2.as[Person]
      person2.id mustBe 2
      
      val result3: Future[Result] = route(app, FakeRequest(GET, "/persons/1")).get
      val apiResult3 = contentAsJson(result3)
      
      val perResult: Person = apiResult3.as[Person]
      perResult.document mustBe "1051"
      
      val result4: Future[Result] = route(app, FakeRequest(GET, "/persons?name=Dario")).get
      val apiResult4 = contentAsJson(result4)
      
      val seqResult: Seq[Person] = apiResult4.as[Seq[Person]]
      seqResult(0).document mustBe "1051"
      
    }

  }
  
}