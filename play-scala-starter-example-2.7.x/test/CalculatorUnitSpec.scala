import org.scalatestplus.play._
import play.api.mvc._
import services.Calculator
import models._
import play.api.test.Helpers._
import controllers.CalculatorController
import play.api.test._
import play.api.libs.json._
import models.OutData
import models.Resolve
import models.ResolveOperation
import models.Operation

class CalculatorUnitSpec extends PlaySpec with Results {

  val calculator: Calculator = new Calculator {
    def add(data: InData): OutData = OutData(data.value1, data.value2, "+", data.value1 + data.value2)

    def subtract(data: InData): OutData = OutData(data.value1, data.value2, "+", data.value1 - data.value2)

    def multiply(data: InData): OutData = OutData(data.value1, data.value2, "+", data.value1 * data.value2)

    def divide(data: InData): OutData = OutData(data.value1, data.value2, "+", data.value1 / data.value2)
  }

  "CalculatorController#ADD" should {

    "return a valid result with action ADD" in {

      val controller = new CalculatorController(stubControllerComponents(), calculator)
      val testData = InData(10, 30)

      val fake = FakeRequest().withJsonBody(Json.toJson(testData));
      val result = controller.add(fake)
      val apiResult = contentAsJson(result)
      val spec = Json.fromJson[OutData](apiResult)

      spec match {
        case JsSuccess(data: OutData, path: JsPath) => {
          data.result mustBe 40;
        }
        case e => fail
      }

    }

  }
  
  "CalculatorController#SUBTRACT" should {

    "return a valid result with action SUBTRACT" in {

      val controller = new CalculatorController(stubControllerComponents(), calculator)
      val testData = InData(50, 30)

      val fake = FakeRequest().withJsonBody(Json.toJson(testData));
      val result = controller.subtract(fake)
      val apiResult = contentAsJson(result)
      val spec = Json.fromJson[OutData](apiResult)

      spec match {
        case JsSuccess(data: OutData, path: JsPath) => {
          data.result mustBe 20;
        }
        case e => fail
      }

    }

  }
  
  "CalculatorController#MULTIPLY" should {

    "return a valid result with action MULTIPLY" in {

      val controller = new CalculatorController(stubControllerComponents(), calculator)
      val testData = InData(10, 30)

      val fake = FakeRequest().withJsonBody(Json.toJson(testData));
      val result = controller.multiply(fake)
      val apiResult = contentAsJson(result)
      val spec = Json.fromJson[OutData](apiResult)

      spec match {
        case JsSuccess(data: OutData, path: JsPath) => {
          data.result mustBe 300;
        }
        case e => fail
      }

    }

  }
  
  "CalculatorController#DIVIDE" should {

    "return a valid result with action DIVIDE" in {

      val controller = new CalculatorController(stubControllerComponents(), calculator)
      val testData = InData(30, 10)

      val fake = FakeRequest().withJsonBody(Json.toJson(testData));
      val result = controller.divide(fake)
      val apiResult = contentAsJson(result)
      val spec = Json.fromJson[OutData](apiResult)

      spec match {
        case JsSuccess(data: OutData, path: JsPath) => {
          data.result mustBe 3;
        }
        case e => fail
      }

    }

  }
  
  
   "Resolve#Operation" should {

    "return valid result with operations" in {

      val resolve: Resolve = new ResolveOperation();
      
      val resultAdd = resolve.apply(Operation.ADD, 1, 1);
      resultAdd.result mustBe 2
      
      val resultSub = resolve.apply(Operation.SUBTRACT, 5, 3);
      resultSub.result mustBe 2
      
      val resultMul = resolve.apply(Operation.MULTIPLY, 1, 2);
      resultMul.result mustBe 2
      
      val resultDivide = resolve.apply(Operation.DIVIDE, 10, 5);
      resultDivide.result mustBe 2

    }

  }
  

}