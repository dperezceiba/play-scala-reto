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
class PersonController @Inject() (persons: Persons, cc: ControllerComponents)
  extends AbstractController(cc) {

  def findById(id: Long) = Action { implicit request =>
    persons.findById(id).fold(NotFound("Person not found"))(p => Ok(Json.toJson(p)))
  }
  
  def findAllByName(name: Option[String]) = Action { implicit request =>
    Ok(Json.toJson(persons.findAllByName(name)))
  }
  
  def save = Action { implicit request =>
    println(request.body)
    val dataFromJson: JsResult[Person] = Json.fromJson[Person](request.body.asJson.get)
    dataFromJson match {
      case JsSuccess(data: Person, path: JsPath) => {
        Ok(Json.toJson(persons.save(data)))
      }
      case e: JsError => InternalServerError("Errors: " + JsError.toJson(e).toString())
    }
  }
  
}