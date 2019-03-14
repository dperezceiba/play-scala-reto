package wsclient

import org.scalatest.concurrent.ScalaFutures
import org.scalatestplus.play._
import play.api.libs.ws.WSClient
import play.api.mvc.Results
import play.api.test.Helpers._
import play.api.test.WsTestClient
import scala.concurrent.ExecutionContext.Implicits.global

class WsClientServerSpec extends PlaySpec
  with Results
  with ScalaFutures{
  
  val url: String = "https://jsonplaceholder.typicode.com/comments";
  
   "Server query should" should {

    "work" in {

      WsTestClient.withClient { ws: WSClient =>
        ws.url(url).get().map { response =>
          response.status mustBe 200
        }
      }
    }
  }
  
}