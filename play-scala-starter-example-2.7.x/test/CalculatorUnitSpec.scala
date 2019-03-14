import org.scalatestplus.play._
import play.api.mvc._
import services.Calculator
import play.api.test.Helpers._
import controllers.CalculatorController
import play.api.test._
import play.api.libs.json._
import domain.InData
import domain.OutData
import domain.OutData
import services.Resolve
import services.impl.ResolveOperation

class CalculatorUnitSpec extends PlaySpec with Results {

  val calculator: Calculator = new Calculator {

    def calculate(data: InData): OutData = {
      data.operation match {
        case "add"      => OutData(data.value1, data.value2, data.operation, data.value1 + data.value2)
        case "subtract" => OutData(data.value1, data.value2, data.operation, data.value1 - data.value2)
        case "multiply" => OutData(data.value1, data.value2, data.operation, data.value1 * data.value2)
        case "divide"   => OutData(data.value1, data.value2, data.operation, data.value1 / data.value2)
        case _          => OutData(data.value1, data.value2, data.operation, 0)
      }
    }
  }

  "CalculatorController#ADD" should {

    "return a valid result with action ADD" in {

      val controller = new CalculatorController(stubControllerComponents(), calculator)
      val testData = InData(10, 30, "add")

      val fake = FakeRequest().withJsonBody(Json.toJson(testData));
      val result = controller.calculate(fake)
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
      val testData = InData(50, 30, "subtract")

      val fake = FakeRequest().withJsonBody(Json.toJson(testData));
      val result = controller.calculate(fake)
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
      val testData = InData(10, 30, "multiply")

      val fake = FakeRequest().withJsonBody(Json.toJson(testData));
      val result = controller.calculate(fake)
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
      val testData = InData(30, 10, "divide")

      val fake = FakeRequest().withJsonBody(Json.toJson(testData));
      val result = controller.calculate(fake)
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

      val resultAdd = resolve.apply("add", 1, 1);
      resultAdd.result mustBe 2

      val resultSub = resolve.apply("subtract", 5, 3);
      resultSub.result mustBe 2

      val resultMul = resolve.apply("multiply", 1, 2);
      resultMul.result mustBe 2

      val resultDivide = resolve.apply("divide", 10, 5);
      resultDivide.result mustBe 2

    }

  }

}