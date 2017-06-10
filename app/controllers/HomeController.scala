package controllers

import javax.inject._

import models.entity.User
import models.service.UserService
import play.api._
import play.api.mvc._

/**
 * This controller creates an `Action` to handle HTTP requests to the
 * application's home page.
 */

trait Generator
{

  def generatorId = java.util.UUID.randomUUID.toString.replace("-","")

}

@Singleton
class HomeController @Inject()(user: UserService) extends Controller with Generator{

  /**
   * Create an Action to render an HTML page with a welcome message.
   * The configuration in the `routes` file means that this method
   * will be called when the application receives a `GET` request with
   * a path of `/`.
   */
  def index = Action {
     Ok(views.html.index("sad"))
  }

}
