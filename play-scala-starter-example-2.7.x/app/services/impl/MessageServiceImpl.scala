package services.impl
import javax.inject._
import play.api.cache._
import scala.concurrent._
import akka.Done
import scala.concurrent.duration._
import domain._
import scala.util.Success
import scala.util.Failure
import services._

@Singleton()
class MessageServiceImpl @Inject()(cache: AsyncCacheApi)(implicit ec: ExecutionContext)
  extends MessageService{
  
  override def send(message: Message) = Future[Message]{
    val count: Future[Option[Int]] = cache.get[Int](message.user)
    count.map(value => cache.set(message.user, value.getOrElse(0) + 1, 15.minute))
    message
  }
  
  override def countMessage(user: String): Future[Int] = {
    cache.get[Int](user).map(value => value.fold(0)(v => v))
  }
  
}