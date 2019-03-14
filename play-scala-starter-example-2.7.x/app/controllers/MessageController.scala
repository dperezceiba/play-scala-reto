package controllers

import javax.inject._
import play.api._
import play.api.mvc._
import services._
import domain._
import play.api._
import play.api.mvc._
import play.api.libs.json._
import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future

class MessageController @Inject() (messageService: MessageService, cc: ControllerComponents)
  extends AbstractController(cc){
  
   def send = Action.async { implicit request =>
    println(request.body)
    val dataFromJson: JsResult[Message] = Json.fromJson[Message](request.body.asJson.get)
    dataFromJson match {
      case JsSuccess(data: Message, path: JsPath) => {
        messageService.send(data).map(result => Ok(Json.toJson(result)))
      }
      case e: JsError => Future(InternalServerError("Errors: " + JsError.toJson(e).toString()))
    }
  }
   
  def countMessage(user: String) = Action.async { implicit request =>
    val result = messageService.countMessage(user)
    result.map(value => Ok(Json.toJson(value)))
  }
  
}