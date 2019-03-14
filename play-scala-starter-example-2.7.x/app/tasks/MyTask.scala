package tasks

import akka.actor.Actor
import java.util.Date

class MyTask extends Actor{
  
  def receive = {
    case name: String =>{  val fecha = new Date(); println(s"$fecha ---> Recibiendo tarea $name")}
  }
  
}