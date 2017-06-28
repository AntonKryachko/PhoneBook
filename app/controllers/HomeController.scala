package controllers

import javax.inject._

import controllers.helpers.Generator
import models.entity.User
import models.service.{UserService, UserServiceImpl}
import play.api.mvc._
import play.api.data._
import play.api.data.Forms._
import play.api.libs.json.Json

/**
 * This controller creates an `Action` to handle HTTP requests to the
 * application's home page.
 */


@Singleton
class HomeController @Inject()(userService: UserService) extends Controller with Generator{


  def index(error: String) = Action {
    Ok(viewMain.html.index(userService.selectAll2().sortBy(_.name),Option(error)))
  }

  val addUser = Form(
    mapping(
      "id" -> text,
      "name" -> text,
      "phoneNumber" -> text
    )(User.apply)(User.unapply)
  )

  def addNewUser = Action {
    implicit request =>
      val user = addUser.bindFromRequest.get.copy(id = generatorId)
      if(isNameExist(user.name)){
        userService.insert(user)
        Redirect(routes.HomeController.index())
      }else{
        Redirect(routes.HomeController.index(user.name))
      }
  }

  def isNameExist(name: String): Boolean = {
   val users = userService.selectAll2()
    !users.exists(_.name == name)
  }

  def updateUser = Action {
    implicit request =>
      val user = addUser.bindFromRequest.get
      userService.update2(user)
      Redirect(routes.HomeController.index())
  }

  def getUser(id: String) = Action {
    implicit request => Ok(Json.toJson(userService.selectById(id)))
  }

  def getAllUsers = Action {
    Ok(Json.toJson(userService.selectAll2()))
  }

  def removeUser(id: String) = Action {
    implicit request =>
      userService.delete(id)
      Redirect(routes.HomeController.index())
  }

  def fullSearch(name: String = "") = Action {
    val users = userService.selectAll2().filter(_.name.contains(name))
    Ok(viewMain.html.index(users.sortBy(_.name)))
  }
}
