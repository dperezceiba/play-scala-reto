package controllers

import javax.inject._
import services._
import play.api._
import play.api.mvc._
import play.api.libs.json._
import scala.concurrent.{ ExecutionContext, Future }
import domain._
import scala.util.Success
import scala.util.Failure
import exceptions.RuleException

class PersonCrudController @Inject() (
  personService: PersonService,
  cc:            ControllerComponents)(implicit ec: ExecutionContext)
  extends AbstractController(cc) {

  def save = Action.async { implicit request =>
    println(request.body)
    val personFromJson: JsResult[PersonCrud] = Json.fromJson[PersonCrud](request.body.asJson.get)
    personFromJson match {
      case JsSuccess(data: PersonCrud, path: JsPath) => {
        val result = personService.save(data);
        result.map(value => Ok(Json.toJson(value))).recoverWith { case e: RuleException => Future.apply(InternalServerError(e.getMessage)) }
      }
      case e: JsError => Future.apply(InternalServerError("Errors: " + JsError.toJson(e).toString()))
    }
  }

  def findById(id: Long) = Action.async { implicit request =>
    personService.findById(id).map(s => s.fold(NotFound("Person not found"))(p => Ok(Json.toJson(p))))
  }

  def delete(id: Long) = Action.async { implicit request =>
    personService.delete(id).map(v => {
      if (v > 0) {
        Ok(s"$v persons deleted with id $id")
      } else {
        NotFound(s"Not found persons with id $id")
      }
    })
  }

  def update(id: Long) = Action.async { implicit request =>
    println(request.body)
    val personFromJson: JsResult[PersonCrud] = Json.fromJson[PersonCrud](request.body.asJson.get)

    personFromJson match {
      case JsSuccess(data: PersonCrud, path: JsPath) => {
        val result = personService.update(id, data)
        result.map(v => {
          if (v > 0) {
            Ok(s"$v persons update with id $id")
          } else {
            NotFound(s"Not found persons with id $id")
          }
        }).recoverWith { case e: RuleException => Future.apply(InternalServerError(e.getMessage)) }
      }
      case e: JsError => Future.apply(InternalServerError("Errors: " + JsError.toJson(e).toString()))
    }
  }

  def findAll = Action.async { implicit request =>
    personService.findAll().map(prop => {
      Ok(Json.toJson(prop))
    })
  }

}