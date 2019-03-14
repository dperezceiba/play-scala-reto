package controllers

import javax.inject._
import play.api._
import play.api.mvc._
import play.api.libs.json._
import play.api.libs.functional.syntax._
import domain._
import scala.concurrent.{ ExecutionContext, Future }
import scala.util._
import services._

@Singleton()
class CalculatorController @Inject() (cc: ControllerComponents, calculator: Calculator)
  extends AbstractController(cc) {
  
  def calculate = Action { implicit request =>
    println(request.body)
    val dataFromJson: JsResult[InData] = Json.fromJson[InData](request.body.asJson.get)
    dataFromJson match {
      case JsSuccess(data: InData, path: JsPath) => {
        Ok(Json.toJson(calculator.calculate(data)))
      }
      case e: JsError => InternalServerError("Errors: " + JsError.toJson(e).toString())
    }
  }
  
}