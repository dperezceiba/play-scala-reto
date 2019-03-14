package controllers

import javax.inject._
import play.api.libs.ws._
import play.api._
import play.api.mvc._
import play.api.libs.json._
import play.api.libs.functional.syntax._
import scala.concurrent.{ ExecutionContext, Future }

class ConsumeClientController @Inject() (
  ws: WSClient,
  cc: MessagesControllerComponents)(implicit ec: ExecutionContext)
  extends MessagesAbstractController(cc) {

  val url: String = "https://jsonplaceholder.typicode.com/comments";

  def findAllComments() = Action.async { implicit request =>
    val request: WSRequest = ws.url(url)

    val complexRequest: WSRequest =
      request.addHttpHeaders("Accept" -> "application/json")
        .addQueryStringParameters("search" -> "play")

    val futureResponse: Future[WSResponse] = complexRequest.get()
    futureResponse.map(p => Ok(p.body).withHeaders("Content-Type" -> "application/json"))
  }

}