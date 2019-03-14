import org.scalatestplus.play.PlaySpec
import org.scalatestplus.play.guice.GuiceOneAppPerSuite
import play.api.http.Status
import play.api.test.FakeRequest
import play.api.test.Helpers._
import play.api.libs.json._
import domain.InData

/**
 * Functional tests start a Play application internally, available
 * as `app`.
 */
class CalculatorFunctionalSpec extends PlaySpec with GuiceOneAppPerSuite {

  "CalculatorController" should {

    "return operations add calculator" in {
      val testData = InData(30, 10, "add")
  
      contentAsString(route(app, FakeRequest(POST, "/calculate").withJsonBody(Json.toJson(testData))).get) must include("40")
    }
    
    "return operations subtract calculator" in {
      val testData = InData(30, 10, "subtract")
  
      contentAsString(route(app, FakeRequest(POST, "/calculate").withJsonBody(Json.toJson(testData))).get) must include("20")
    }
    
    "return operations multiply calculator" in {
      val testData = InData(30, 10, "multiply")
  
      contentAsString(route(app, FakeRequest(POST, "/calculate").withJsonBody(Json.toJson(testData))).get) must include("300")
    }
    
    "return operations divide calculator" in {
      val testData = InData(30, 10, "divide")
  
      contentAsString(route(app, FakeRequest(POST, "/calculate").withJsonBody(Json.toJson(testData))).get) must include("3")
    }
    
    "return operations other calculator" in {
      val testData = InData(30, 10, "other")
  
      contentAsString(route(app, FakeRequest(POST, "/calculate").withJsonBody(Json.toJson(testData))).get) must include("0")
    }

  }
}
