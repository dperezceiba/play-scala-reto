package controllers

import javax.inject._

import play.api._
import play.api.mvc._
import akka.actor.ActorSystem
import scala.concurrent.duration._
import scala.concurrent.ExecutionContext
import tasks.MyTask
import akka.actor.Props

@Singleton()
class TaskController @Inject()(cc: ControllerComponents,system: ActorSystem)(implicit ec: ExecutionContext) 
extends AbstractController(cc){
  
  def tasks = Action {
     val taskActor = system.actorOf(Props(classOf[MyTask]), "task-actor")
    println("Iniciando Task Actor!")
    system.scheduler.schedule(5.seconds, 5.seconds, taskActor, "Revisar")
    Ok
  }
  
}