import akka.actor.ActorSystem
import controllers.{AsyncController, CountController}
import org.scalatestplus.play._
import play.api.test.Helpers._
import play.api.test.FakeRequest
import services.Counter
import services.Calculator
import services.Calculator
import controllers.CalculatorController
import play.api.test.FakeRequest
import play.api.libs.json.Json
import play.api.libs.json.JsResult

/**
 * Unit tests can run without a full Play application.
 */
class UnitSpec extends PlaySpec {

  "CountController" should {

    "return a valid result with action" in {
      val counter: Counter = new Counter {
        override def nextCount(): Int = 49
      }
      val controller = new CountController(stubControllerComponents(), counter)
      val result = controller.count(FakeRequest())
      contentAsString(result) must equal("49")
    }
  }

  "AsyncController" should {

    "return a valid result on action.async" in {
      // actor system will create threads that must be cleaned up even if test fails
      val actorSystem = ActorSystem("test")
      try {
        implicit val ec = actorSystem.dispatcher
        val controller = new AsyncController(stubControllerComponents(), actorSystem)
        val resultFuture = controller.message(FakeRequest())
        contentAsString(resultFuture) must be("Hi!")
      } finally {
        // always shut down actor system at the end of the test.
        actorSystem.terminate()
      }
    }
  }

}