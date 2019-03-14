package services

import javax.inject._
import play.api.cache._
import scala.concurrent._
import domain._

trait MessageService {
  def send(message: Message): Future[Message]
  
  def countMessage(user:String): Future[Int]
  
}
