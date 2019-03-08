package filters

import javax.inject._
import play.api.mvc._
import scala.concurrent.ExecutionContext

/**
 * This is a simple filter that adds a header to all requests. It's
 * added to the application's list of filters by the
 * [[Filters]] class.
 *
 * @param ec This class is needed to execute code asynchronously.
 * It is used below by the `map` method.
 */
@Singleton
class ExampleFilter @Inject()(implicit ec: ExecutionContext) extends EssentialFilter {
  override def apply(next: EssentialAction) = EssentialAction { request =>
    
    val headers: Seq[(String, String)] = request.headers.headers
    
    for (h <- headers) {
      println(s"header => : $h")
    }  
    
    next(request).map { result =>
      //result.as("application/json")
      result.withHeaders("X-ExampleFilter" -> "foo")
    }
  }
}