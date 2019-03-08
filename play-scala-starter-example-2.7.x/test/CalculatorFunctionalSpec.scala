import org.scalatestplus.play.PlaySpec
import org.scalatestplus.play.guice.GuiceOneAppPerSuite
import play.api.http.Status
import play.api.test.FakeRequest
import play.api.test.Helpers._
import play.api.libs.json._
import models.InData

/**
 * Functional tests start a Play application internally, available
 * as `app`.
 */
class CalculatorFunctionalSpec extends PlaySpec with GuiceOneAppPerSuite {

  "CalculatorController" should {

    "return operations calculator" in {
      val testData = InData(30, 10)
  
      contentAsString(route(app, FakeRequest(POST, "/calculator/add").withJsonBody(Json.toJson(testData))).get) must include("40")
      contentAsString(route(app, FakeRequest(POST, "/calculator/subtract").withJsonBody(Json.toJson(testData))).get) must include("20")
      contentAsString(route(app, FakeRequest(POST, "/calculator/multiply").withJsonBody(Json.toJson(testData))).get) must include("300")
      contentAsString(route(app, FakeRequest(POST, "/calculator/divide").withJsonBody(Json.toJson(testData))).get) must include("3")
    }

  }
}
